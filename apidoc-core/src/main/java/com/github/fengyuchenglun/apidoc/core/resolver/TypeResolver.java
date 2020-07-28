package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.javaparser.resolution.types.ResolvedType;

/**
 * 类型解析器.
 *
 * @author duanledexianxianxian
 */
public interface TypeResolver {

    /**
     * Accept boolean.
     *
     * @param type the type
     * @return the boolean
     */
    boolean accept(ResolvedType type);

    /**
     * Resolve type description.
     *
     * @param type the type
     * @return the type description
     */
    TypeDescription resolve(ResolvedType type);

}
