package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.StringTypeDescription;
import com.github.javaparser.resolution.types.ResolvedType;
import com.google.common.collect.ImmutableList;

/**
 * The type Date type resolver.
 *
 * @author duanledexianxianxian
 */
public class DateTypeResolver implements TypeResolver {
    @Override
    public boolean accept(ResolvedType type) {
        return isDate(type);
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        return new StringTypeDescription(type.asReferenceType().getTypeDeclaration().getName(),"");
    }

    /**
     * Is date boolean.
     *
     * @param type the type
     * @return the boolean
     */
    private static boolean isDate(ResolvedType type){
        if(!type.isReferenceType()){
            return false;
        }
        return isDate(type.asReferenceType().getId());
    }

    /**
     * Is date boolean.
     *
     * @param id the id
     * @return the boolean
     */
    private static boolean isDate(String id){
        return ImmutableList.of("java.util.Date",
                "java.time.LocalDateTime",
                "java.time.LocalDate",
                "java.time.LocalTime"
        ).contains(id);
    }
}
