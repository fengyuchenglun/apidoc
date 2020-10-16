package com.github.fengyuchenglun.apidoc.springmvc.example.annotation;
import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义controller注解
 * 正常执行流程统一结果返回
 *
 * @author duanledexianxianxian
 * @date 2018 /11/6
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Controller
@KimResponseBody
public @interface KimController {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     *
     * @return the suggested component name, if any (or empty String otherwise)
     */
    String value() default "";
}
