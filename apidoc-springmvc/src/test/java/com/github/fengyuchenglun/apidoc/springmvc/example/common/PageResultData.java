package com.github.fengyuchenglun.apidoc.springmvc.example.common;

import com.kim.boot.web.converter.result.domain.Page;
import com.kim.boot.web.resolver.result.ResultResolvable;

import java.io.Serializable;

/**
 * 返回结果.
 *
 * @param <T> 泛型
 * @author duanledexianxianxian
 * @date 2018 /11/9
 * @pageResultData1
 */
public class PageResultData<T> implements Serializable, ResultResolvable {
    private static final long serialVersionUID = 1L;
    /**
     * 结果数据.
     */
    private Page<T> data;
    /**
     * 结果返回编码.
     */
    private String code = "sys.success";
    /**
     * 结果消息.
     */
    private String message;
    /**
     * traceId
     * 用于日志追踪
     */
    private String traceId;

    /**
     * 构造函数
     */
    public PageResultData() {
    }

    /**
     * 构造函数
     *
     * @param traceId the trace id
     * @param data    the data
     */
    public PageResultData(String traceId, Page<T> data) {
        setData(data);
        setTraceId(traceId);
    }

    /**
     * 构造函数
     *
     * @param traceId the trace id
     * @param code    the code
     * @param message the result
     * @param data    the data
     */
    public PageResultData(String traceId, String code, String message, Page<T> data) {
        this(traceId, data);
        setCode(code);
        setMessage(message);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Page<T> getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Page<T> data) {
        this.data = data;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getTraceId() {
        return traceId;
    }

    /**
     * Sets trace id.
     *
     * @param traceId the trace id
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
