package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.UnAvailableTypeDescription;
import com.github.javaparser.resolution.types.ResolvedType;
import com.google.common.collect.ImmutableList;

/**
 * 不支持直接使用Map，建议使用DTO
 *
 * @author duanledexianxianxian
 */
public class MapTypeResolver implements TypeResolver {
    /**
     * Is map boolean.
     *
     * @param type the type
     * @return the boolean
     */
    private static boolean isMap(ResolvedType type) {
        if (!type.isReferenceType()) {
            return false;
        }
        return isMap(type.asReferenceType().getId());
    }

    /**
     * Is map boolean.
     *
     * @param id the id
     * @return the boolean
     */
    private static boolean isMap(String id) {
        return ImmutableList.of("java.util.Map",
                "java.util.HashMap",
                "java.util.Hashtable",
                "java.util.SortedMap",
                "java.util.LinkedHashMap",
                "java.lang.TreeMap"
        ).contains(id);
    }

    @Override
    public boolean accept(ResolvedType type) {
        return isMap(type);
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        return new UnAvailableTypeDescription();
    }
}
