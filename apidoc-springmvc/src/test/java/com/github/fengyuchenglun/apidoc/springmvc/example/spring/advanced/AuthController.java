package com.github.fengyuchenglun.apidoc.springmvc.example.spring.advanced;

import com.github.fengyuchenglun.apidoc.springmvc.example.common.ResultData;
import com.github.fengyuchenglun.apidoc.springmvc.example.common.User;
import com.github.fengyuchenglun.apidoc.springmvc.example.spring.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证模块.
 *
 * @index 3
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    /**
     * 获取认证信息.
     *
     * @param token 上报的身份验证token，jwt
     * @return result data
     */
    @PostMapping
    public ResultData<User> getAuthInfo(@RequestHeader String token) {
        return ResultData.ok();
    }


    /**
     * 检查auth是否有效.
     * 将忽略此接口
     *
     * @param token the token
     * @return boolean boolean
     * @ignore
     */
    @PostMapping
    public Boolean checkAuth(@RequestHeader String token) {
        return false;
    }
}
