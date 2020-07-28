package com.github.fengyuchenglun.apidoc.core.schema;

import lombok.extern.slf4j.Slf4j;

/**
 * 支持的http method
 * @author fengyuchenglun
 */
@Slf4j
public enum Method {


    /**
     * Get method.
     */
    GET,
    /**
     * Post method.
     */
    POST,
    /**
     * Put method.
     */
    PUT,
    /**
     * Delete method.
     */
    DELETE,
    /**
     * Options method.
     */
    OPTIONS,
    /**
     * Patch method.
     */
    PATCH,
    /**
     * Copy method.
     */
    COPY,
    /**
     * Head method.
     */
    HEAD,
    /**
     * Link method.
     */
    LINK,
    /**
     * Unlink method.
     */
    UNLINK,
    /**
     * Purge method.
     */
    PURGE,
    /**
     * Lock method.
     */
    LOCK,
    /**
     * Unlock method.
     */
    UNLOCK,
    /**
     * Propfind method.
     */
    PROPFIND,
    /**
     * View method.
     */
    VIEW;

    /**
     * Of method.
     *
     * @param name the name
     * @return the method
     */
    public static Method of(String name) {
        return valueOf(name);
    }

}
