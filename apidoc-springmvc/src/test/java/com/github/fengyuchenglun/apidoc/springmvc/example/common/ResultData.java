package com.github.fengyuchenglun.apidoc.springmvc.example.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回结果.
 * 有的项目会封装ResultData类,封装请求返回。
 * 但是在Controller中
 * 1. 调用者需显示声明ResultData返回类型。
 * 2. 调用者需显示创建ResultData对象。
 *
 * @param <T> the type parameter
 */
@Setter
@Getter
public class ResultData<T> {

    /**
     * 返回码
     */
    int code;
    /**
     * 返回信息.
     */
    String msg;
    /**
     * The Data.
     * @data
     */
    T data;

    /**
     * Ok result data.
     *
     * @param <T> the type parameter
     * @return the result data
     */
    public static <T> ResultData<T> ok() {
        return ok(null);
    }

    /**
     * Ok result data.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the result data
     */
    public static <T> ResultData<T> ok(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.code = 0;
        resultData.msg = "ok";
        resultData.data = data;
        return resultData;
    }

}
