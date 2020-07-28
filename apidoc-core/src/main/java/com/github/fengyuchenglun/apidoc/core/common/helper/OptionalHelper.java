package com.github.fengyuchenglun.apidoc.core.common.helper;

import java.util.Optional;

/**
 * optional帮助类
 *
 * @author duanledexianxianxian
 */
public class OptionalHelper {

    /**
     * Any optional.
     *
     * @param <T>       the type parameter
     * @param optionals the optionals
     * @return the optional
     */
    @SafeVarargs
    public static <T> Optional<T> any(Optional<T>... optionals) {
        for (Optional<T> optional : optionals) {
            if (optional.isPresent()) {
                return optional;
            }
        }
        return Optional.empty();
    }

}
