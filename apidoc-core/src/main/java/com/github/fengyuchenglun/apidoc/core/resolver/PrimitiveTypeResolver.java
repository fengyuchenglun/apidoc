package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.PrimitiveTypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.javaparser.resolution.types.ResolvedType;
import com.google.common.collect.ImmutableList;

/**
 * 原始类型解析.
 *
 * @author duanledexianxianxian
 */
public class PrimitiveTypeResolver implements TypeResolver {
    /**
     * 是否为包装类型.
     *
     * @param type the type
     * @return the boolean
     */
    private static boolean isBoxing(ResolvedType type) {
        if (!type.isReferenceType()) {
            return false;
        }
        String id = type.asReferenceType().getId();
        return ImmutableList.of(
                "java.lang.Boolean",
                "java.lang.Character",
                "java.lang.Double",
                "java.lang.Float",
                "java.lang.Long",
                "java.lang.Integer",
                "java.lang.Short",
                "java.lang.Byte"
        ).contains(id);
    }

    @Override
    public boolean accept(ResolvedType type) {
        // type.isPrimitive()  javaparser
        return type.isPrimitive() || isBoxing(type);
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        if (type.isPrimitive()) {
            return new PrimitiveTypeDescription(type.asPrimitive());
        } else {
            return new PrimitiveTypeDescription(type.asReferenceType());
        }
    }

}
