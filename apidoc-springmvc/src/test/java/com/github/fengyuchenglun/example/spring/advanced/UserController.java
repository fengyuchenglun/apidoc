package com.github.fengyuchenglun.example.spring.advanced;

import com.github.fengyuchenglun.example.common.Query;
import com.github.fengyuchenglun.example.common.User;
import com.github.fengyuchenglun.example.common.UserQuery;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户接口.
 *
 * @author duanledexianxianxian
 * @version 1.0.0
 * @date 2020 /3/25 1:00
 * @index 20
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    /**
     * 查看用户详情
     *
     * @param userId 用户编号
     * @return the user
     */
    @PostMapping(value = "/{userId}")
    public User detail(@PathVariable("userId")Long userId) {
        User user = new User();
        return user;
    }

    /**
     * 查看用户详情
     * 测试get的query对象
     *
     * @param query 过滤条件
     * @return 用户对象 user
     */
    @GetMapping(value = "/detail1")
    public User detail1(Query query) {
        User user = new User();
        return user;
    }


    /**
     *
     * 测试返回List
     *
     * @param query 过滤条件
     * @return 用户对象 user
     */
    @GetMapping(value = "/getList")
    public List<User> getList(Query query) {
        return null;
    }

    /**
     *
     * 测试返回List
     *
     * @param query 过滤条件
     * @return 用户对象 user
     */
    @GetMapping(value = "/getStringList")
    public List<String> getStringList(Query query) {
        return null;
    }


    /**
     *
     * 测试返回List
     *
     * @param query 过滤条件
     * @return 用户对象 user
     */
    @GetMapping(value = "/getStringArrayList")
    public List<List<String>> getStringArrayList(Query query) {
        return null;
    }

    /**
     * 测试get的query对象,带RequestParam注解
     *
     * @param query the query
     * @return 用户对象 user
     */
    @GetMapping(value = "/detail3")
    public User detail3(Query query) {
        User user = new User();
        return user;
    }

    /**
     * 测试get的query对象,带RequestParam注解,required=false
     *
     * @param query 过滤条件
     * @return 用户对象 user
     */
    @GetMapping(value = "/detail4")
    public User detail4(@RequestParam(required = false) String query) {
        User user = new User();
        return user;
    }

    /**
     * get-无查询参数
     *
     * @return 用户对象 user
     */
    @GetMapping(value = "/detail5")
    public List<User> detail5() {
        return null;
    }

    /**
     * get-原始对象+查询对象
     *
     * @param userName the user name
     * @param age      the age
     * @param query    the query
     * @return 用户对象 user
     */
    @GetMapping(value = "/detail6")
    public User detail6(String userName, Integer age, UserQuery query) {
        User user = new User();
        return user;
    }

    /**
     * post-
     *
     * @return 用户对象 user
     */
    @GetMapping(value = "/post1")
    public UserQuery post1() {
        return null;
    }


    /**
     * 删除用户
     *
     * @param userId the user id
     * @return boolean 是否成功
     */
    @DeleteMapping(value = "/{userId}")
    public Boolean deleteUser(@PathVariable("userId") Long userId) {
        return true;
    }


    /**
     * 用户表单对象.
     *
     * @author duanledexianxianxian
     */
    @Data
    public static class UserForm implements Serializable {

        /**
         * The constant serialVersionUID.
         */
        private static final long serialVersionUID = 5681371348688016281L;
        /**
         * 用户名
         */
        private String userName;
        /**
         * 地址
         */
        private String address;
        /**
         * 年龄
         */
        private Integer age;
    }

    /**
     * 添加用户
     *
     * @param form 用户表单对象
     * @return 返回记录 boolean
     */
    @PostMapping
    public Boolean add(@RequestBody UserForm form) {
        return null;
    }
}
