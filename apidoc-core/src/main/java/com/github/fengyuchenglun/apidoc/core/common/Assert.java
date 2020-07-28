package com.github.fengyuchenglun.apidoc.core.common;

import com.google.common.base.Strings;

/**
 * The type Assert.
 *
 * @author duanledexianxianxian
 */
public class Assert {

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
     * Not blank.
     *
     * @param text    the text
     * @param message the message
     */
    public static void notBlank(String text, String message) {
        if (Strings.isNullOrEmpty(text) || Strings.isNullOrEmpty(text.trim())) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Between.
     *
     * @param num     the num
     * @param min     the min
     * @param max     the max
     * @param message the message
     */
    public static void between(int num, int min, int max, String message) {
        if (num < min || num > max) {
            throw new IllegalArgumentException(message);
        }

    }

}
