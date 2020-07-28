package com.github.fengyuchenglun.apidoc.core.parser;

import com.github.fengyuchenglun.apidoc.core.schema.Chapter;
import com.github.fengyuchenglun.apidoc.core.schema.Section;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;


/**
 * 解析策略接口
 * @author fengyuchenglun
 */
public interface ParserStrategy {

    /**
     * Name string.
     *
     * @return the string
     */
    String name();

    /**
     * On load.
     */
    void onLoad();

    /**
     * 判断是否为需要解析的类
     *
     * @param n the n
     * @return boolean boolean
     */
    boolean accept(ClassOrInterfaceDeclaration n);

    /**
     * 判断是否为需要解析的方法
     *
     * @param n the n
     * @return boolean boolean
     */
    boolean accept(MethodDeclaration n);

    /**
     * Visit.
     *
     * @param n       the n
     * @param chapter the chapter
     * @param section the section
     */
    void visit(MethodDeclaration n, Chapter chapter, Section section);

    /**
     * Visit.
     *
     * @param n       the n
     * @param chapter the chapter
     */
    void visit(ClassOrInterfaceDeclaration n, Chapter chapter);
}
