package com.github.fengyuchenglun.apidoc.core.schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

/**
 * 章，一个类解析为一章
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Chapter extends Node {
    /**
     * The Book name.
     */
    String bookName;


    /**
     * The Sections.
     */
    Set<Section> sections = new TreeSet<>();

}
