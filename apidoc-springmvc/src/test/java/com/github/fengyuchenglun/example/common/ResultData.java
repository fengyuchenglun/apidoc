package com.github.fengyuchenglun.example.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回结果对象.
 *
 * @param <T> the type parameter
 * @author fengyuchenglun
 * @version 1.0.0
 * @resultData
 */
@Setter
@Getter
public class ResultData<T> {

    /**
     * 返回码
     */
    int code;
    /**
     * The Msg.
     */
//返回信息
    String msg;
    /**
     * 数据对象.
     *
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
