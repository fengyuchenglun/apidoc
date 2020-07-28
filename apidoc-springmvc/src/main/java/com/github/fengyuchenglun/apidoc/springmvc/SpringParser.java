package com.github.fengyuchenglun.apidoc.springmvc;

import com.github.fengyuchenglun.apidoc.core.common.description.ArrayTypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.helper.AnnotationHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.ClassDeclarationHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.ExpressionHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.StringHelper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.URI;
import com.github.fengyuchenglun.apidoc.core.common.description.ObjectTypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.parser.ParserStrategy;
import com.github.fengyuchenglun.apidoc.core.schema.Chapter;
import com.github.fengyuchenglun.apidoc.core.schema.Header;
import com.github.fengyuchenglun.apidoc.core.schema.Row;
import com.github.fengyuchenglun.apidoc.core.schema.Section;
import com.github.fengyuchenglun.apidoc.springmvc.resovler.SpringComponentTypeResolver;

import java.util.List;
import java.util.Optional;

import static com.github.fengyuchenglun.apidoc.core.common.Constants.TAG_JAVA_DOC_RETURN;
import static com.github.fengyuchenglun.apidoc.core.schema.ParameterType.*;


/**
 * spring 解析
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
public class SpringParser implements ParserStrategy {

    /**
     * The constant FRAMEWORK.
     */
    public static final String FRAMEWORK = "springmvc";


    /**
     * The constant EXT_URI.
     */
    public static final String EXT_URI = "uri";


    @Override
    public String name() {
        return FRAMEWORK;
    }

    @Override
    public void onLoad() {
        // 添加类型解析器
        ApiDoc.getInstance().getTypeResolvers().addResolver(new SpringComponentTypeResolver());
        // 类型名称解析器
        ApiDoc.getInstance().getTypeResolvers().addNameResolver(new SpringComponentTypeResolver());
    }

    /**
     * 处理被@RestController和@Controller标记的类
     *
     * @return classOrInterfaceDeclaration
     */
    @Override
    public boolean accept(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
        return AnnotationHelper.isAnnotationPresent(classOrInterfaceDeclaration, SpringMVCContext.getInstance().getControllers());
    }

    /**
     * 类被@RestController标记，或方法被@ResponseBody标记
     *
     * @param methodDeclaration methodDeclaration
     * @return boolean
     */
    @Override
    public boolean accept(MethodDeclaration methodDeclaration) {
        //类被@RestController标记，或方法被@ResponseBody标记
        return RequestMappingHelper.isRest(methodDeclaration) && AnnotationHelper.isAnnotationPresent(methodDeclaration, RequestMappingHelper.ANNOTATION_REQUEST_MAPPINGS);
    }

    /**
     * 解析类定义
     *
     * @param n
     * @param chapter
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration n, Chapter chapter) {
        chapter.getExt().put(EXT_URI, RequestMappingHelper.pickUriToParent(n));
    }

    /**
     * 解析方法定义
     *
     * @param n       方法声明
     * @param chapter 章
     * @param section 节
     */
    @Override
    public void visit(MethodDeclaration n, Chapter chapter, Section section) {
        // 解析方法
        visitMethod(n, chapter, section);
        // 解析url
        visitUri(n, chapter, section);
        // 解析路径参数
        visitPathVariable(n, chapter, section);
        // 解析头
        visitHeaders(n, chapter, section);
        // 解析请求参数
        visitParameters(n, chapter, section);
        // 解析返回结果
        visitReturn(n, chapter, section);
    }

    /**
     * 解析请求方法
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    private void visitMethod(MethodDeclaration n, Chapter chapter, Section section) {
        section.setMethod(RequestMappingHelper.pickMethod(n));
    }

    /**
     * 解析请求URI，与父类URI拼接
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    private void visitUri(MethodDeclaration n, Chapter chapter, Section section) {
        URI uri = (URI) chapter.getExt().get(EXT_URI);
        section.setUri(new URI(uri.toString()).add(RequestMappingHelper.pickUri(n.getAnnotations())).toString());
    }

    /**
     * 解析方法参数
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    private void visitParameters(MethodDeclaration n, Chapter chapter, Section section) {
        for (Parameter parameter : n.getParameters()) {
            if (ParameterHelper.isRequestBody(parameter)) {
                visitRequestBody(n, chapter, section);
            } else {
                visitParameter(n, chapter, section);
            }
        }
    }

    /**
     * 解析PathVariable
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    private void visitPathVariable(MethodDeclaration n, Chapter chapter, Section section) {
        for (Parameter parameter : n.getParameters()) {
            if (ParameterHelper.isPathVariable(parameter)) {
                section.getPathVariable().put(parameter.getNameAsString(), "");
                Row row = new Row();
                row.setParameterType(PATH.getMsg());
                row.setKey(parameter.getNameAsString());
                row.setType(parameter.getType().toString());
                // 路径参数必填
                row.setRequired(true);
                section.getParamTag(row.getKey()).ifPresent(tag -> row.setRemark(tag.getContent()));
                section.addRequestRow(row);
            }
        }
    }

    /**
     * 解析RequestHeader
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    private void visitHeaders(MethodDeclaration n, Chapter chapter, Section section) {

        List<String> headers = RequestMappingHelper.pickHeaders(n.getAnnotations());
        for (String text : headers) {
            section.addInHeader(Header.valueOf(text));
        }

        List<String> consumers = RequestMappingHelper.pickConsumers(n.getAnnotations());
        if (!consumers.isEmpty()) {
            section.addInHeader(new Header("Content-Type", String.join(",", consumers)));
        }

        List<String> produces = RequestMappingHelper.pickProduces(n.getAnnotations());
        if (!produces.isEmpty()) {
            section.addOutHeader(new Header("Content-Type", String.join(",", produces)));
        }

        for (Parameter parameter : n.getParameters()) {
            if (ParameterHelper.isRequestHeader(parameter)) {
                String key = parameter.getNameAsString();
                String defaultValue = "{value}";
                AnnotationExpr annotationExpr = parameter.getAnnotationByName(ParameterHelper.ANNOTATION_REQUEST_HEADER).get();
                Optional<Expression> valueOptional = AnnotationHelper.getAnyAttribute(annotationExpr, "value", "name");
                if (valueOptional.isPresent()) {
                    key = String.valueOf(ExpressionHelper.getValue(valueOptional.get()));
                }
                Optional<Expression> defaultValueOptional = AnnotationHelper.getAttribute(annotationExpr, "defaultValue");
                if (defaultValueOptional.isPresent()) {
                    defaultValue = String.valueOf(ExpressionHelper.getValue(defaultValueOptional.get()));
                }
                TypeDescription description = ApiDoc.getInstance().getTypeResolvers().resolve(parameter.getType());
                if (description.isAvailable()) {
                    Object value = description.getValue();
                    if (StringHelper.isBlank(defaultValue) && StringHelper.nonBlank(value)) {
                        defaultValue = String.valueOf(value);
                    }
                    section.addInHeader(new Header(key, defaultValue));
                }
            }
        }
    }

    /**
     * 解析RequestBody
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    private void visitRequestBody(MethodDeclaration n, Chapter chapter, Section section) {
        section.setQueryParameter(false);
        section.addInHeader(Header.APPLICATION_JSON);
        for (Parameter parameter : n.getParameters()) {
            if (ParameterHelper.isRequestBody(parameter)) {
                TypeDescription description = ApiDoc.getInstance().getTypeResolvers().resolve(parameter.getType());
                if (description.isAvailable()) {
                    if (description.isArray()) {
                        section.setRequestBodyParameters(description.asArray().getValue());
                    } else if (description.isObject()) {
                        section.setRequestBodyParameters(description.asObject().getValue());
                    }
                    section.addRequestRows(description.rows(BODY.getMsg()));
                }
                break;
            }
        }

    }

    /**
     * 解析RequestParameter
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    private void visitParameter(MethodDeclaration n, Chapter chapter, Section section) {
        ObjectTypeDescription objectTypeDescription = new ObjectTypeDescription();
        for (Parameter parameter : n.getParameters()) {
            if (ParameterHelper.isRequestParam(parameter)) {
                String key = parameter.getNameAsString();

                Object defaultValue = null;
                Boolean required = null;

                // 是否带有@RequestParam注解
                Optional<AnnotationExpr> optional = parameter.getAnnotationByName(ParameterHelper.ANNOTATION_REQUEST_PARAM);
                if (optional.isPresent()) {
                    // 如果有RequestParam注解，则参数必填
                    required = true;
                    Optional<Expression> valueOptional = AnnotationHelper.getAnyAttribute(optional.get(), "value", "name");
                    if (valueOptional.isPresent()) {
                        key = String.valueOf(ExpressionHelper.getValue(valueOptional.get()));
                    }
                    Optional<Expression> defaultValueOptional = AnnotationHelper.getAttribute(optional.get(), "defaultValue");
                    if (defaultValueOptional.isPresent()) {
                        defaultValue = ExpressionHelper.getValue(defaultValueOptional.get());
                    }
                    Optional<Expression> requiredOptional = AnnotationHelper.getAttribute(optional.get(), "required");
                    if (requiredOptional.isPresent() && requiredOptional.get().isBooleanLiteralExpr()) {
                        required = requiredOptional.get().asBooleanLiteralExpr().getValue();
                    }
                }

                TypeDescription description = ApiDoc.getInstance().getTypeResolvers().resolve(parameter.getType());
                if (description.isAvailable()) {
                    section.getParamTag(key).ifPresent(tag -> description.addRemark(tag.getContent()));
                    if (required != null) {
                        description.setRequired(required);
                    }
                    if (description.isObject()) {
                        objectTypeDescription.merge(description.asObject());
                    } else {
                        description.setKey(key);
                        if (defaultValue != null && (description.isPrimitive() || description.isString())) {
                            description.setDefaultValue(defaultValue);
                        }
                        objectTypeDescription.add(description);
                    }
                }
            }
        }
        section.setQueryParameters(objectTypeDescription.getValue());
        section.addRequestRows(objectTypeDescription.rows(QUERY.getMsg()));
    }

    /**
     * 解析方法返回参数
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    private void visitReturn(MethodDeclaration n, Chapter chapter, Section section) {
        ClassOrInterfaceDeclaration resultDataClassOrInterfaceDeclaration = ApiDoc.getInstance().getResultDataClassOrInterfaceDeclaration();
        if (null != resultDataClassOrInterfaceDeclaration) {
            ClassOrInterfaceType returnType = new ClassOrInterfaceType();
            returnType.setName(resultDataClassOrInterfaceDeclaration.getName());
            returnType.setTypeArguments(n.getType());
            n.findCompilationUnit().get().addImport(ClassDeclarationHelper.getClassOrInterfacePackageName(resultDataClassOrInterfaceDeclaration));
            n.setType(returnType);
            section.setIsResultData(true);
        }
        TypeDescription description = ApiDoc.getInstance().getTypeResolvers().resolve(n.getType());
        if (description.isAvailable()) {
            if (description.isPrimitive()) {
                section.setRawResponse(description.getValue());
                handleResultData(section,description);
            } else if (description.isString()) {
                section.setRawResponse(description.getValue());
                handleResultData(section,description);
            } else if (description.isArray()) {
                ArrayTypeDescription asArray = description.asArray();
                if (asArray.getComponent().isPrimitive() || asArray.getComponent().isString()) {
                    handleResultData(section,description);
                }
                section.setResponse(description.asArray().getValue());
            } else if (description.isObject()) {
                section.setResponse(description.asObject().getValue());
            }

            section.addResponseRows(description.rows());
        }
    }

    private void handleResultData(Section section, TypeDescription description) {
        if (!section.getIsResultData()) {
            description.setKey("result");
            section.getTag(TAG_JAVA_DOC_RETURN).ifPresent(x -> description.setRemark(x.getContent()));

        }
    }

}
