package com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.vo;

import com.kim.boot.web.converter.result.domain.PageSortQuery;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户返回对象.
 */
public class UserVO implements Serializable {
    private static final long serialVersionUID = 3386062077229886409L;
    /**
     * 用户编号.
     */
    private Integer userId;

    /**
     * 用户名.
     * @mock duanledexianxianxian
     */
    private String userName;

    /**
     * 用户头像.
     */
    private String avatar;
    /**
     * 账号生效时间
     * 保留 暂时不用
     */
    private LocalDateTime effDate;

    /**
     * 账号失效时间
     * 保留 暂时不用
     */
    private LocalDateTime expDate;


    /**
     * 手机号码.
     */
    private String mobile;

    /**
     * 固定号码.
     */
    private String fixedPhone;

    /**
     * 邮箱.
     */
    private String email;

    /**
     * 用户状态
     * 0 无效 1有效
     */
    private Integer userState;

    /**
     * 锁定状态
     * 0-未锁  1-锁定
     */
    private Integer lockState;


    /**
     * 用户角色编号列表.
     */
    private List<Integer> userRoleRelationList;
}
