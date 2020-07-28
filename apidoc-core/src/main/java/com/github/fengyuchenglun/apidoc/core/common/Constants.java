package com.github.fengyuchenglun.apidoc.core.common;

import java.nio.charset.StandardCharsets;

/**
 * 常量类
 *
 * @author duanledexianxianxian
 * @version 1.0.0
 * @date 2020 /3/26 23:00
 * @since 1.0.0
 */
public class Constants {
    /**
     * The constant UTF8.
     */
    public static final String UTF8 = StandardCharsets.UTF_8.name();
    /**
     * The constant SLASH.
     */
    public static final String SLASH = "/";
    /**
     * The constant MARKDOWN_EXTENSION.
     */
    public static final String MARKDOWN_EXTENSION = ".md";
    /**
     * markdown模版文件默认路径
     */
    public static final  String MARKDOWN_TEMPLATE = "/templates/markdown.ftl";
    /**
     * 自定义tag-mock
     */
    public static final String TAG_CUSTOM_JAVA_DOC_MOCK = "mock";
    /**
     * 自定义tag-data
     */
    public static final String TAG_CUSTOM_JAVA_DOC_DATA = "data";
    /**
     * javadoc-tag-return
     */
    public static final String TAG_JAVA_DOC_RETURN = "return";

    /**
     * 空格符
     */
    public static final  String FIELD_SPACE = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

}
