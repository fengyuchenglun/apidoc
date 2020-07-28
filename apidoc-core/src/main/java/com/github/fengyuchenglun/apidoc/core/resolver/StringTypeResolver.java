package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.StringTypeDescription;
import com.github.javaparser.resolution.types.ResolvedType;
import com.google.common.collect.ImmutableList;

/**
 * The type String type resolver.
 *
 * @author duanledexianxianxian
 */
public class StringTypeResolver implements TypeResolver {
    @Override
    public boolean accept(ResolvedType type) {
        return isString(type);
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        return new StringTypeDescription("String","");
    }

    /**
     * Is string boolean.
     *
     * @param type the type
     * @return the boolean
     */
    private static boolean isString(ResolvedType type){
        if(!type.isReferenceType()){
            return false;
        }
        return isString(type.asReferenceType().getId());
    }

    /**
     * Is string boolean.
     *
     * @param id the id
     * @return the boolean
     */
    private static boolean isString(String id){
        return ImmutableList.of("java.lang.String",
                "java.lang.CharSequence"
        ).contains(id);
    }
}
