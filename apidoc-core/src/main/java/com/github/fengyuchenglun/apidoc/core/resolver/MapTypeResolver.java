package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.description.ObjectTypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.UnAvailableTypeDescription;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.model.typesystem.ReferenceTypeImpl;
import com.github.javaparser.utils.Pair;
import com.google.common.collect.ImmutableList;

import java.util.List;

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
        List<Pair<ResolvedTypeParameterDeclaration, ResolvedType>> typeParametersMap = ((ReferenceTypeImpl) type).getTypeParametersMap();
        if (typeParametersMap != null && typeParametersMap.size() == 2) {
            TypeDescription key = null;
            TypeDescription value = null;
            ObjectTypeDescription typeDescription = new ObjectTypeDescription();
            if (!"?".equals(typeParametersMap.get(0).b.describe())) {
                key = ApiDoc.getInstance().getTypeResolvers().resolve(typeParametersMap.get(0).b);
            }
            if (!"?".equals(typeParametersMap.get(1).b.describe())) {
                value = ApiDoc.getInstance().getTypeResolvers().resolve(typeParametersMap.get(1).b);
            }
            if (key != null && value != null) {
                typeDescription.setKey(key.getKey());
                typeDescription.add(value);
                return typeDescription;
            }
        }
        return new UnAvailableTypeDescription();
    }
}
