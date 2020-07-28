package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.UnAvailableTypeDescription;
import com.github.javaparser.resolution.types.ResolvedType;

/**
 * The type System object type resolver.
 *
 * @author duanledexianxianxian
 */
public class SystemObjectTypeResolver implements TypeResolver {
    @Override
    public boolean accept(ResolvedType type) {
        return isSystem(type);
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        return new UnAvailableTypeDescription();
    }

    /**
     * Is system boolean.
     *
     * @param type the type
     * @return the boolean
     */
    private static boolean isSystem(ResolvedType type){
        if(!type.isReferenceType()){
            return false;
        }
        return isSystem(type.asReferenceType().getId());
    }

    /**
     * Is system boolean.
     *
     * @param id the id
     * @return the boolean
     */
    private static boolean isSystem(String id){
        return id!=null && (id.startsWith("java") ||id.startsWith("sun"));
    }
}
