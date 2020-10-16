package com.github.fengyuchenglun.apidoc.springmvc.example.common;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Wrapper.
 *
 * @param <T> the type parameter
 */
@Setter
@Getter
public class Wrapper<T> {

    /**
     * The Wrapper.
     */
    String wrapper;

    /**
     * The Data.
     */
    T data;

}
