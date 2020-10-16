package com.github.fengyuchenglun.apidoc.springmvc.example.common;

/**
 * 返回编码.
 *
 * @code
 */
public enum Code {

    /**
     * Ok code.
     */
    OK(0, "ok"),
    /**
     * Error code.
     */
    ERROR(-1, "error"),
    /**
     * The No auth.
     */
    NoAuth(1, "no auth");

    /**
     * 编码
     */
    private int code;
    /**
     * 返回信息
     */
    private String text;

    Code(int code, String text) {
        this.code = code;
        this.text = text;
    }
}