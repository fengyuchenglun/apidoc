package com.github.fengyuchenglun.apidoc.core.schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

/**
 * The type Book.
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class Book extends Node {

    /**
     * book 默认名称.
     */
    public static final String DEFAULT = "index";

    /**
     * 章
     */
    Set<Chapter> chapters = new TreeSet<>();

    /**
     * Instantiates a new Book.
     *
     * @param id the id
     */
    public Book(String id) {
        this.id = id;
    }
}
