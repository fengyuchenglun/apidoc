package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.helper.*;
import com.github.fengyuchenglun.apidoc.core.schema.Tag;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.description.ObjectTypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static com.github.fengyuchenglun.apidoc.core.common.Constants.TAG_CUSTOM_JAVA_DOC_IGNORE;
import static com.github.fengyuchenglun.apidoc.core.common.Constants.TAG_CUSTOM_JAVA_DOC_REPLACE;

/**
 * java bean解析
 *
 * @author duanledexianxianxian
 */
public class ObjectTypeResolver implements TypeResolver {

    @Override
    public boolean accept(ResolvedType type) {
        return type.isReferenceType();
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {

        ResolvedReferenceType referenceType = type.asReferenceType();
        ObjectTypeDescription typeDescription = new ObjectTypeDescription();

        typeDescription.setType(referenceType.getTypeDeclaration().getName());
        if (referenceType.getTypeDeclaration() instanceof JavaParserClassDeclaration) {
            ((JavaParserClassDeclaration) referenceType.getTypeDeclaration()).getWrappedNode().getComment().ifPresent(typeDescription::accept);
        }
        //类型解析缓冲池，防止循环引用
        if (!ReferenceContext.getInstance().push(referenceType.describe())) {
            return typeDescription;
        }

        //解析父类属性，并合并至当前
        for (ResolvedReferenceType directAncestor : referenceType.getDirectAncestors()) {
            TypeDescription ancestorDescription = ApiDoc.getInstance().getTypeResolvers().resolve(directAncestor);
            if (ancestorDescription.isAvailable() && ancestorDescription.isObject()) {
                typeDescription.merge(ancestorDescription.asObject());
            }
        }


        //TODO fix use access method
        for (ResolvedFieldDeclaration declaredField : referenceType.getTypeDeclaration().getDeclaredFields()) {
            if (declaredField.isStatic()) {
                continue;
            }
            if (CommentHelper.isFieldTagPresent(declaredField, TAG_CUSTOM_JAVA_DOC_IGNORE)) {
                continue;
            }
            ResolvedType fieldType = declaredField.getType();

            if (fieldType.isReferenceType()) {
                //将父类的T，传递给 属性的T
                fieldType = TypeParameterHelper.useClassTypeParameter(referenceType, fieldType.asReferenceType());
            }
            if (declaredField.getType().isTypeVariable()) {
                //类型为T，这种泛型
                Optional<ResolvedType> optional = TypeParameterHelper.getTypeParameter(referenceType, declaredField.getType().asTypeParameter().getName());
                if (optional.isPresent()) {
                    fieldType = optional.get();
                }
            }

            // 字段注释带有replace标签
            if (CommentHelper.isFieldTagPresent(declaredField, TAG_CUSTOM_JAVA_DOC_REPLACE)) {
                TypeDescription fieldTypeDescription = ApiDoc.getInstance().getTypeResolvers().resolve(fieldType);
                if (fieldTypeDescription.isAvailable() && fieldTypeDescription.isObject()) {
                    typeDescription.merge(fieldTypeDescription.asObject());
                }
                continue;
            }

            TypeDescription fieldDescription = ApiDoc.getInstance().getTypeResolvers().resolve(fieldType);
            fieldDescription.setKey(declaredField.getName());
            //查找json别名
            JsonPropertyHelper.getJsonName(declaredField).ifPresent(fieldDescription::setKey);
            //解析注释
            String comment = CommentHelper.getComment(declaredField);
            fieldDescription.addRemark(comment);
            //查找Validation注解
            for (String validation : ValidationHelper.getValidations(declaredField)) {
                fieldDescription.getCondition().append(validation).append(" ");
            }
            //查找字段初始化值
            setDefault(declaredField, fieldDescription);

            typeDescription.add(fieldDescription);
        }

        ReferenceContext.getInstance().remove(referenceType.describe());
        return typeDescription;
    }


    /**
     * Sets default.
     *
     * @param declaredField    the declared field
     * @param fieldDescription the field description
     */
    private void setDefault(ResolvedFieldDeclaration declaredField, TypeDescription fieldDescription) {
        Optional<Comment> optional = CommentHelper.getOptionalComment(declaredField);
        if (optional.isPresent()) {
            Comment comment = optional.get();
            if (comment.isJavadocComment()) {
                Javadoc javadoc = comment.asJavadocComment().parse();
                // 设置tag
                javadoc.getBlockTags().forEach(blockTag -> {
                    Tag tag = new Tag();
                    tag.setId(blockTag.getTagName());
                    tag.setKey(blockTag.getName().isPresent() ? blockTag.getName().get() : "");
                    tag.setContent(CommentHelper.getDescription(blockTag.getContent()));
                    fieldDescription.putTag(tag);
                });
            }
            FieldHelper.getInitializer(declaredField).ifPresent(expr -> fieldDescription.setDefaultValue(ExpressionHelper.getValue(expr)));

        }


    }

}