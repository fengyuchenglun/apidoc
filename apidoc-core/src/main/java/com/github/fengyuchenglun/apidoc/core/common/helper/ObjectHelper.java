package com.github.fengyuchenglun.apidoc.core.common.helper;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * The type Object helper.
 *
 * @author duanledexianxianxian
 */
public class ObjectHelper {
    /**
     * Is empty boolean.
     *
     * @param object the object
     * @return the boolean
     */
    public static boolean isEmpty(final Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        }
        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).isEmpty();
        }
        if (object instanceof Map<?, ?>) {
            return ((Map<?, ?>) object).isEmpty();
        }
        return false;
    }
}
