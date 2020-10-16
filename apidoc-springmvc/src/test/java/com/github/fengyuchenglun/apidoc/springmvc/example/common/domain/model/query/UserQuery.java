package com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.query;

import com.kim.boot.web.converter.result.domain.PageSortQuery;

import java.io.Serializable;

/**
 * 用户表单对象.
 */
public class UserQuery extends PageSortQuery implements Serializable {
    private static final long serialVersionUID = 3386062077229886409L;

    /**
     * 用户名.
     */
    private String userName;
    /**
     * 用户状态.
     */
    private Integer userState;
    /**
     * 锁定状态.
     */
    private Integer lockState;

}
