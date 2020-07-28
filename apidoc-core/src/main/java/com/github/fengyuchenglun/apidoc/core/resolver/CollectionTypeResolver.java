package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.helper.TypeParameterHelper;
import com.github.fengyuchenglun.apidoc.core.common.description.ArrayTypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.UnAvailableTypeDescription;
import com.github.javaparser.resolution.types.ResolvedType;
import com.google.common.collect.ImmutableList;

import java.util.Optional;

/**
 * The type Collection type resolver.
 *
 * @author duanledexianxianxian
 */
public class CollectionTypeResolver implements TypeResolver {
    @Override
    public boolean accept(ResolvedType type) {
        return isCollection(type);
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        Optional<ResolvedType> optional = TypeParameterHelper.getTypeParameter(type.asReferenceType(), 0);
        return new ArrayTypeDescription(optional
                .map(ApiDoc.getInstance().getTypeResolvers()::resolve)
                .orElseGet(UnAvailableTypeDescription::new));
    }

    /**
     * Is collection boolean.
     *
     * @param type the type
     * @return the boolean
     */
    private static boolean isCollection(ResolvedType type){
        if(!type.isReferenceType()){
            return false;
        }
        return isCollection(type.asReferenceType().getId());
    }

    /**
     * Is collection boolean.
     *
     * @param id the id
     * @return the boolean
     */
    private static boolean isCollection(String id){
        return ImmutableList.of("java.util.List",
                "java.util.Collection",
                "java.util.ArrayList",
                "java.util.LinkedList",
                "java.util.Set",
                "java.util.HashSet",
                "java.util.Iterator",
                "java.lang.Iterable"
        ).contains(id);

    }
}
