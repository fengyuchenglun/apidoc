package com.github.fengyuchenglun.apidoc.springmvc.example.common;

/**
 * 用户角色
 *
 * @code
 */
public enum Role {

    /**
     * Admin role.
     */
    ADMIN("管理员"),
    /**
     * User role.
     */
    USER("用户"),
    /**
     * Vip role.
     */
    VIP("会员");

    /**
     * The Text.
     */
    String text;

    /**
     * Instantiates a new Role.
     *
     * @param text the text
     */
    Role(String text) {
        this.text = text;
    }
}
