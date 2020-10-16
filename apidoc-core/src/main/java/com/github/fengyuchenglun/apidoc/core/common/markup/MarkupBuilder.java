package com.github.fengyuchenglun.apidoc.core.common.markup;

import com.github.fengyuchenglun.apidoc.core.common.markup.asciidoc.AsciiDocBuilder;

import java.util.List;
import java.util.function.Consumer;

/**
 * 文档构建器
 * 只实现满足需求的部分即可
 */
public interface MarkupBuilder {

    /**
     * Get instance markup builder.
     *
     * @return the markup builder
     */
    static MarkupBuilder getInstance(){
        return new AsciiDocBuilder();
    }

    /**
     * 文档头
     *
     * @param text  标题
     * @param attrs 文档属性
     * @return this builder
     */
    MarkupBuilder header(String text, CharSequence... attrs);

    /**
     * 文档标题
     *
     * @param level [1,5]
     * @param text  标题
     * @return this builder
     */
    MarkupBuilder title(int level, String text);

    /**
     * 行内文字
     *
     * @param text text
     * @return this builder
     */
    MarkupBuilder text(String text);

    /**
     * 单行文字
     *
     * @param text text 换行将被替换为空格
     * @return this builder
     */
    MarkupBuilder textLine(String text);

    /**
     * 多行文字
     *
     * @param text  the text
     * @param attrs the attrs
     * @return the markup builder
     */
    MarkupBuilder paragraph(String text, CharSequence... attrs);

    /**
     * 带icon的各种段落
     *
     * @param text the text
     * @return the markup builder
     */
    MarkupBuilder note(String text);

    /**
     * Tip markup builder.
     *
     * @param text the text
     * @return the markup builder
     */
    MarkupBuilder tip(String text);

    /**
     * Important markup builder.
     *
     * @param text the text
     * @return the markup builder
     */
    MarkupBuilder important(String text);

    /**
     * Warning markup builder.
     *
     * @param text the text
     * @return the markup builder
     */
    MarkupBuilder warning(String text);

    /**
     * Caution markup builder.
     *
     * @param text the text
     * @return the markup builder
     */
    MarkupBuilder caution(String text);

    /**
     * 各种block
     *
     * @param consumer the consumer
     * @param flag     the flag
     * @param attrs    the attrs
     * @return the markup builder
     */
    MarkupBuilder block(Consumer<MarkupBuilder> consumer, CharSequence flag, CharSequence... attrs);

    /**
     * Listing markup builder.
     *
     * @param consumer the consumer
     * @param attrs    the attrs
     * @return the markup builder
     */
    MarkupBuilder listing(Consumer<MarkupBuilder> consumer, CharSequence... attrs);

    /**
     * Literal markup builder.
     *
     * @param consumer the consumer
     * @param attrs    the attrs
     * @return the markup builder
     */
    MarkupBuilder literal(Consumer<MarkupBuilder> consumer, CharSequence... attrs);

    /**
     * Sidebar markup builder.
     *
     * @param consumer the consumer
     * @param attrs    the attrs
     * @return the markup builder
     */
    MarkupBuilder sidebar(Consumer<MarkupBuilder> consumer, CharSequence... attrs);

    /**
     * Comment markup builder.
     *
     * @param consumer the consumer
     * @param attrs    the attrs
     * @return the markup builder
     */
    MarkupBuilder comment(Consumer<MarkupBuilder> consumer, CharSequence... attrs);

    /**
     * Passthrough markup builder.
     *
     * @param consumer the consumer
     * @param attrs    the attrs
     * @return the markup builder
     */
    MarkupBuilder passthrough(Consumer<MarkupBuilder> consumer, CharSequence... attrs);

    /**
     * Quote markup builder.
     *
     * @param consumer the consumer
     * @param attrs    the attrs
     * @return the markup builder
     */
    MarkupBuilder quote(Consumer<MarkupBuilder> consumer, CharSequence... attrs);

    /**
     * Example markup builder.
     *
     * @param consumer the consumer
     * @param attrs    the attrs
     * @return the markup builder
     */
    MarkupBuilder example(Consumer<MarkupBuilder> consumer, CharSequence... attrs);

    /**
     * 列表 默认数字
     *
     * @param text the text
     * @return markup builder
     */
    MarkupBuilder list(String text);

    /**
     * 列表 默认数字
     *
     * @param text the text
     * @param flag 数字，字母，罗马字母
     * @return markup builder
     */
    MarkupBuilder list(String text, CharSequence flag);

    /**
     * 链接
     *
     * @param text the text
     * @param url  the url
     * @return markup builder
     */
    MarkupBuilder url(String text, String url);

    /**
     * Image markup builder.
     *
     * @param text the text
     * @param url  the url
     * @return markup builder
     */
    MarkupBuilder image(String text, String url);

    /**
     * 表格 默认第一组数据为表头
     *
     * @param data the data
     * @return markup builder
     */
    MarkupBuilder table(List<List<String>> data);

    /**
     * Table markup builder.
     *
     * @param data   the data
     * @param header the header
     * @param footer the footer
     * @return the markup builder
     */
    MarkupBuilder table(List<List<String>> data, boolean header, boolean footer);


    /**
     * 强调
     *
     * @param text      text
     * @param textStyle the text style
     * @return this builder
     */
    MarkupBuilder emphasized(String text, CharSequence... textStyle);

    /**
     * 加粗
     *
     * @param text      text
     * @param textStyle the text style
     * @return this builder
     */
    MarkupBuilder strong(String text, CharSequence... textStyle);

    /**
     * 等宽
     *
     * @param text      text
     * @param textStyle the text style
     * @return this builder
     */
    MarkupBuilder monospaced(String text, CharSequence... textStyle);

    /**
     * 单引号
     *
     * @param text      text
     * @param textStyle the text style
     * @return this builder
     */
    MarkupBuilder quoted(String text, CharSequence... textStyle);

    /**
     * 双引号
     *
     * @param text      text
     * @param textStyle the text style
     * @return this builder
     */
    MarkupBuilder doubleQuoted(String text, CharSequence... textStyle);

    /**
     * 正常的引用文字
     *
     * @param text      text
     * @param textStyle the text style
     * @return this builder
     */
    MarkupBuilder unquoted(String text, CharSequence... textStyle);


    /**
     * 换行
     *
     * @return this builder
     */
    MarkupBuilder br();

    /**
     * 强制换行
     *
     * @return this builder
     */
    MarkupBuilder hbr();

    /**
     * 另起一行
     *
     * @return this builder
     */
    MarkupBuilder newLine();

    /**
     * 横线
     *
     * @return this builder
     */
    MarkupBuilder pageBreak();

    /**
     * 获取文件内容
     *
     * @return content content
     */
    String getContent();

    /**
     * 清空content
     */
    void clean();

}

