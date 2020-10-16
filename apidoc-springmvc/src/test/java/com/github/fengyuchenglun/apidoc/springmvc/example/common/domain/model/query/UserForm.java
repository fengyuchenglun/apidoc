package com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.query;

import com.kim.boot.web.converter.result.domain.Edit;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户表单对象.
 */
public class UserForm implements Serializable {
    private static final long serialVersionUID = 3386062077229886409L;
    /**
     * 用户编号.
     */
    @NotNull(message = "用户编号不能为空", groups = {Edit.class})
    private Integer userId;


    /**
     * 用户名.
     */
    @NotBlank(message = "用户名不能为空")
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
    @NotNull(message = "用户状态不能为空")
    private Integer userState;

    /**
     * 锁定状态
     * 0-未锁  1-锁定
     */
    @NotNull(message = "validation.user.lock.state.null")
    private Integer lockState;


    /**
     * 用户角色编号列表.
     */
    @NotEmpty(message = "用户角色不能为空")
    private List<Integer> userRoleRelationList;
}
