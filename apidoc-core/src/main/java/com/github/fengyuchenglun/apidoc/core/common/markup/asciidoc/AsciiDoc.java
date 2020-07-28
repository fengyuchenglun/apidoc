package com.github.fengyuchenglun.apidoc.core.common.markup.asciidoc;

/**
 * The enum Ascii doc.
 * @author duanledexianxianxian
 */
public enum AsciiDoc implements CharSequence {
    /**
     * Extension ascii doc.
     */
    EXTENSION(".adoc"),
    /**
     * 各种关键字
     */
    HEADER("= "),
    /**
     * Table ascii doc.
     */
    TABLE("|==="),
    /**
     * Table cell ascii doc.
     */
    TABLE_CELL("|"),
    /**
     * Title ascii doc.
     */
    TITLE("="),
    /**
     * Emphasized ascii doc.
     */
    EMPHASIZED("_"),
    /**
     * Strong ascii doc.
     */
    STRONG("*"),
    /**
     * Monospaced ascii doc.
     */
    MONOSPACED("+"),
    /**
     * Quoted ascii doc.
     */
    QUOTED("`"),
    /**
     * Double quoted ascii doc.
     */
    DOUBLE_QUOTED("``"),
    /**
     * Unquoted ascii doc.
     */
    UNQUOTED("#"),
    /**
     * List flag ascii doc.
     */
    LIST_FLAG("1. "),
    /**
     * List flag letter ascii doc.
     */
    LIST_FLAG_LETTER("a. "),
    /**
     * List flag letter upper ascii doc.
     */
    LIST_FLAG_LETTER_UPPER("A. "),
    /**
     * Listing ascii doc.
     */
    LISTING("----"),
    /**
     * Literal ascii doc.
     */
    LITERAL("...."),
    /**
     * Sidebar ascii doc.
     */
    SIDEBAR("****"),
    /**
     * Comment ascii doc.
     */
    COMMENT("////"),
    /**
     * Passthrough ascii doc.
     */
    PASSTHROUGH("++++"),
    /**
     * Quote ascii doc.
     */
    QUOTE("____"),
    /**
     * Example ascii doc.
     */
    EXAMPLE("===="),
    /**
     * Note ascii doc.
     */
    NOTE("NOTE"),
    /**
     * Tip ascii doc.
     */
    TIP("TIP"),
    /**
     * Important ascii doc.
     */
    IMPORTANT("IMPORTANT"),
    /**
     * Warning ascii doc.
     */
    WARNING("WARNING"),
    /**
     * Caution ascii doc.
     */
    CAUTION("CAUTION"),
    /**
     * Pagebreaks ascii doc.
     */
    PAGEBREAKS("<<<"),
    /**
     * Hardbreaks ascii doc.
     */
    HARDBREAKS("[%hardbreaks]"),
    /**
     * Whitespace ascii doc.
     */
    WHITESPACE(" "),
    /**
     * Br ascii doc.
     */
    BR("\r\n"),
    /**
     * New line ascii doc.
     */
    NEW_LINE("\r\n\r\n"),
    /**
     * Hbr ascii doc.
     */
    HBR(" +"),
    /**
     * 文档属性
     */
    TOC(":toc:"),
    /**
     * Left ascii doc.
     */
    LEFT("left"),
    /**
     * Toc level ascii doc.
     */
    TOC_LEVEL(":toclevels:"),
    /**
     * Toc title ascii doc.
     */
    TOC_TITLE(":toc-title:"),
    /**
     * Doctype ascii doc.
     */
    DOCTYPE(":doctype:"),
    /**
     * Book ascii doc.
     */
    BOOK("book"),
    /**
     * Source highlighter ascii doc.
     */
    SOURCE_HIGHLIGHTER(":source-highlighter:"),
    /**
     * Prettify ascii doc.
     */
    PRETTIFY("prettify"),
    /**
     * Highlightjs ascii doc.
     */
    HIGHLIGHTJS("highlightjs"),
    /**
     * Coderay ascii doc.
     */
    CODERAY("coderay"),
    /**
     * 文字样式
     */
    STYLE_BIG("big"),
    /**
     * Style small ascii doc.
     */
    STYLE_SMALL("small"),
    /**
     * Style underline ascii doc.
     */
    STYLE_UNDERLINE("underline"),
    /**
     * Style overline ascii doc.
     */
    STYLE_OVERLINE("overline"),
    /**
     * Style line through ascii doc.
     */
    STYLE_LINE_THROUGH("line-through"),
    ;

    /**
     * The Markup.
     */
    private final String markup;

    /**
     * Instantiates a new Ascii doc.
     *
     * @param markup the markup
     */
    AsciiDoc(final String markup) {
        this.markup = markup;
    }

    @Override
    public int length() {
        return markup.length();
    }

    @Override
    public char charAt(int index) {
        return markup.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return markup.subSequence(start, end);
    }

    @Override
    public String toString() {
        return markup;
    }

    /**
     * Attr char sequence.
     *
     * @param key   the key
     * @param value the value
     * @return the char sequence
     */
    public static CharSequence attr(AsciiDoc key, Object value){
        return key.toString() + " " + String.valueOf(value);
    }

}
