package com.github.fengyuchenglun.apidoc.core.common;

import java.nio.charset.StandardCharsets;

/**
 * 常量类
 *
 * @author duanledexianxianxian
 * @version 1.0.0
 * @since 1.0.0
 */
public class Constants {
    /**
     * 默认-节点索引
     */
    public static final Integer DEFAULT_NODE_INDEX = 99;
    /**
     * 默认-项目编号
     */
    public static final String DEFAULT_PROJECT_ID = "api";
    /**
     * 默认-编译路径
     */
    public static final String DEFAULT_BUILD_PATH = "build";
    /**
     * 默认-代码结构
     */
    public static final String DEFAULT_CODE_STRUCTURE = "src/main/java";
    /**
     * 默认-文档版本
     */
    public static final String DEFAULT_VERSION = "1.0.0";
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
     * markdown模版目录
     */
    public static final String TEMPLATE_PATH = "/templates";
    /**
     * 自定义tag-book
     */
    public static final String TAG_CUSTOM_JAVA_DOC_BOOK = "book";
    /**
     * 自定义tag-group
     */
    public static final String TAG_CUSTOM_JAVA_DOC_GROUP = "group";
    /**
     * 自定义tag-mock
     */
    public static final String TAG_CUSTOM_JAVA_DOC_MOCK = "mock";
    /**
     * 自定义tag-data
     */
    public static final String TAG_CUSTOM_JAVA_DOC_DATA = "data";
    /**
     * 自定义tag-ignore
     */
    public static final String TAG_CUSTOM_JAVA_DOC_IGNORE = "ignore";
    /**
     * 自定义tag-replace
     */
    public static final String TAG_CUSTOM_JAVA_DOC_REPLACE = "replace";
    /**
     * javadoc-tag-return
     */
    public static final String TAG_JAVA_DOC_RETURN = "return";
    /**
     * javadoc-tag-code
     */
    public static final String TAG_JAVA_DOC_CODE = "code";
    /**
     * javadoc-tag-resultData
     */
    public static final String TAG_JAVA_DOC_RESULT_DATA = "resultData";
    /**
     * javadoc-tag-pageResultData
     */
    public static final String TAG_JAVA_DOC_PAGE_RESULT_DATA = "pageResultData";

    /**
     * 空格符
     */
    public static final String FIELD_SPACE = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

}
