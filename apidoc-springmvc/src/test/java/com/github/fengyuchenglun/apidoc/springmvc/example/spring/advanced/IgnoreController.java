package com.github.fengyuchenglun.apidoc.springmvc.example.spring.advanced;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 将被忽略.
 *
 * @ignore
 */
@RestController
@RequestMapping("/ignore")
public class IgnoreController {


    /**
     * Ignore this.
     */
    @RequestMapping
    public void ignoreThis(){
    }


}
