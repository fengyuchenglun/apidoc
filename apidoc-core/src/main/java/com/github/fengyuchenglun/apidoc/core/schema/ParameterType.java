package com.github.fengyuchenglun.apidoc.core.schema;

/**
 * 请求参数枚举类型
 *
 * @author duanledexianxianxian
 * @since 1.0.0
 */
public enum ParameterType {
    /**
     * 查询参数.
     */
    QUERY("QUERY", "查询参数"),
    /**
     * 路径参数.
     */
    PATH("PATH", "路径参数"),
    /**
     * 请求参数体.
     */
    BODY("BODY", "请求体");
    /**
     * 编码
     */
    private String code;
    /**
     * 信息
     */
    private String msg;

    ParameterType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
