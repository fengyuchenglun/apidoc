package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.javaparser.ast.type.Type;

/**
 * 名称解析器.
 * @author 名称解析器
 */
public interface TypeNameResolver {

    /**
     * Accept boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean accept(String id);

    /**
     * Resolve type description.
     *
     * @param type the type
     * @return the type description
     */
    TypeDescription resolve(Type type);

}
