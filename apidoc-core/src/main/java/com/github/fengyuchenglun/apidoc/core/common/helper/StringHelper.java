package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.google.common.base.Strings;

/**
 * The type String helper.
 *
 * @author duanledexianxianxian
 */
public class StringHelper {

    /**
     * Is blank boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public static boolean isBlank(String text) {
        return Strings.isNullOrEmpty(text) || Strings.isNullOrEmpty(text.trim());
    }

    /**
     * Non blank boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public static boolean nonBlank(String text) {
        return !isBlank(text);
    }

    /**
     * Is blank boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public static boolean isBlank(Object text) {
        if (text instanceof String) {
            return isBlank(((String) text));
        }
        return isBlank(String.valueOf(text));
    }

    /**
     * Non blank boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public static boolean nonBlank(Object text) {
        if (text instanceof String) {
            return nonBlank(((String) text));
        }
        return nonBlank(String.valueOf(text));
    }

    /**
     * Join string.
     *
     * @param delimiter the delimiter
     * @param values    the values
     * @return the string
     */
    public static String join(String delimiter, String... values) {
        StringBuilder builder = new StringBuilder();
        for (String value : values) {
            if (isBlank(value)) {
                continue;
            }
            if (builder.length() > 0) {
                builder.append(delimiter);
            }
            builder.append(value);
        }
        return builder.toString();
    }

}
