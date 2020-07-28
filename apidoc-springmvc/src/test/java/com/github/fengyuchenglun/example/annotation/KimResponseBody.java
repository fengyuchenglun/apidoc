package com.github.fengyuchenglun.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义response body注解
 * 正常请求统一结果返回
 *
 * @author duanledexianxianxian
 * @date 2018 /11/6
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface KimResponseBody {
}
