package com.github.fengyuchenglun.apidoc.springmvc.example.spring;

import com.github.fengyuchenglun.apidoc.springmvc.example.common.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * home模块.
 *
 */
@RestController
@RequestMapping("/home")
public class HelloController {
    /**
     * hello接口.
     *
     * @param name 名称
     * @return the greeting
     */
    @GetMapping
    public Greeting hello(String name){
        return null;
    }
}
