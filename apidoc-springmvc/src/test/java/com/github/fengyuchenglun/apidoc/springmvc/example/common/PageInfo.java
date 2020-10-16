package com.github.fengyuchenglun.apidoc.springmvc.example.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Page info.
 */
@Setter
@Getter
public class PageInfo extends Query {

    /**
     * 第几页
     */
    @JsonProperty
    int page = 1;
    /**
     * The Sizes.
     */
    /* 每页条数 */
    @JSONField(name = "limit")
    int sizes = 20;
    /**
     * The Total.
     */
    @SerializedName("totalPage")
    int total = 0;
    /**
     * The Max page.
     */
    @JsonProperty("max")
    int maxPage = 0;
                                                                                                                                                                                                                                                                                    }
