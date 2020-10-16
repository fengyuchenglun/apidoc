package com.github.fengyuchenglun.apidoc.springmvc.example.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * The type Query.
 */
@Setter
@Getter
public class Query {

    /**
     * static will be ignore
     */
    public static final String CONSTANS = "";

    /**
     * 用户名称
     *
     * @mock 张三
     */
    @NotNull
    @Size(min = 20)
    private String userName;
    /**
     * 用户手机号码
     *
     * @mock 15173253855
     */
    @Size(min = 20)
    private String userPhoneNum;
    /**
     * 用户邮箱
     *
     * @mock fengyuchenglun@foxmail.com
     */
    private String userEmail;
    /**
     * 开始时间
     *
     * @mock 2020-07-25 01:00:00
     */
    private LocalDateTime startTime;

}
