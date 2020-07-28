package com.github.fengyuchenglun.example.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Query.
 */
@Setter
@Getter
public class Query {

//    /**
//     * static will be ignore
//     */
//    public static final String CONSTANS = "";
//
//    /**
//     * 用户名称
//     *
//     * @mock 张三
//     */
//    @NotNull
//    @Size(min = 20)
//    private String userName;
//    /**
//     * 用户手机号码
//     *
//     * @mock 15173253855
//     */
//    @Size(min = 20)
//    private String userPhoneNum;
//    /**
//     * 用户邮箱
//     *
//     * @mock fengyuchenglun@foxmail.com
//     */
//    private String userEmail;
//    /**
//     * 开始时间
//     *
//     * @mock 2020-07-25 01:00:00
//     */
//    private float startTime;
//    /**
//     * 结束时间
//     * 2020-07-25 01:00:00
//
    //

    private LocalDateTime endTime;

    private List<Query> queryList;

}
