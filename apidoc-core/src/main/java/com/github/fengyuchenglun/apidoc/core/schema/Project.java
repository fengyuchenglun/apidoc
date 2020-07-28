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
     * 添加章节.
     *
     * @param chapter the chapter
     */
    public void addChapter(Chapter chapter) {
        if (Objects.isNull(chapter.getBookName())) {
            chapter.setBookName(Book.DEFAULT);
        }
        if (!books.containsKey(chapter.getBookName())) {
            books.put(chapter.getBookName(), new Book(chapter.getBookName()));
        }
        books.get(chapter.getBookName()).getChapters().add(chapter);
    }
}
