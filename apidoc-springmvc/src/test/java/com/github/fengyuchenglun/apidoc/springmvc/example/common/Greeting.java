package com.github.fengyuchenglun.apidoc.springmvc.example.common;

/**
 * Greeting返回对象
 */
public class Greeting {

    /**
     * 编号
     */
    private final long id;
    /**
     * 内容
     */
    private final String content;

    /**
     * Instantiates a new Greeting.
     *
     * @param id      the id
     * @param content the content
     */
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }
}
