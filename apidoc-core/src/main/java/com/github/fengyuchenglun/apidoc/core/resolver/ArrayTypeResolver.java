package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.ArrayTypeDescription;
import com.github.javaparser.resolution.types.ResolvedType;

/**
 * The type Array type resolver.
 *
 * @author duanledexianxianxian
 */
public class ArrayTypeResolver implements TypeResolver {
    @Override
    public boolean accept(ResolvedType type) {
        return type.isArray();
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        return new ArrayTypeDescription(ApiDoc.getInstance().getTypeResolvers().resolve(type.asArrayType().getComponentType()));
    }

}
