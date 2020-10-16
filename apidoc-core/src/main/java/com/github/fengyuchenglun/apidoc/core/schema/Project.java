package com.github.fengyuchenglun.apidoc.core.schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;


/**
 * 项目
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends Node {

    /**
     * 版本
     */
    String version;

    /**
     * The Books.
     */
    Map<String, Book> books = new TreeMap<>();

    /**
     * 附录
     */
    List<Appendix> appendices = new LinkedList<>();

    /**
     * 当前book.
     */
    String currentBook;


    /**
     * 添加章节.
     *
     * @param chapter the chapter
     */
    public void addChapter(Chapter chapter) {
        if (Objects.isNull(chapter.getBookName())) {
            chapter.setBookName(Book.DEFAULT);
        }
        String bookName = chapter.getBookName();
        if (!books.containsKey(bookName)) {
            books.put(bookName, new Book(bookName, bookName));
        }
        books.get(bookName).getChapters().add(chapter);
    }
}
