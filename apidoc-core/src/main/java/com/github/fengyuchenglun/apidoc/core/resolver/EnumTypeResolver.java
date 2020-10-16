package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.StringTypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.javaparser.resolution.declarations.ResolvedEnumConstantDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedEnumDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;

/**
 * 枚举类型解析器.
 *
 * @author duanledexianxianxian
 */
public class EnumTypeResolver implements TypeResolver {
    @Override
    public boolean accept(ResolvedType type) {
        return type.isReferenceType() && type.asReferenceType().getTypeDeclaration().isEnum();
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        ResolvedEnumDeclaration enumDeclaration = type.asReferenceType().getTypeDeclaration().asEnum();
        TypeDescription description = new StringTypeDescription(enumDeclaration.getName(), "");
        for (ResolvedEnumConstantDeclaration enumConstant : enumDeclaration.getEnumConstants()) {
            description.addRemark(enumConstant.getName());
        }
        return description;
    }
}
