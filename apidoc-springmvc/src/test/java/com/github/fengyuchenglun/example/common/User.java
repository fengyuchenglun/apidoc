package com.github.fengyuchenglun.example.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 用户对象
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Setter
@Getter
public class User {

    /**
     * 用户编号.
     */
    int id;
    /**
     * 用户名称.
     */
    @NotBlank
    String name;
    /**
     * 用户年龄.
     * @mock 20
     */
    @Min(1)
    @NotNull
    Integer age;
    /**
     * 创建时间.
     * @mock 2020-07-28
     */
    Date createAt;
    /**
     * 性别.
     */
    @NotBlank
    @JsonProperty("Sex")
    String sex;

}
