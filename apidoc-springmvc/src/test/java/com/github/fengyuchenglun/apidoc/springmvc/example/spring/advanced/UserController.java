package com.github.fengyuchenglun.apidoc.springmvc.example.spring.advanced;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.query.UserForm;
import com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.query.UserQuery;
import com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.vo.UserVO;
import com.kim.boot.web.converter.result.domain.Add;
import com.kim.boot.web.converter.result.domain.Edit;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    /**
     * 查看用户详情
     *
     * @param userId 用户编号
     * @return the user
     * @index 4
     */
    @GetMapping(value = "/{userId}")
    public UserVO getUserDetail(@PathVariable("userId") Long userId) {
        return new UserVO();
    }


    /**
     * 添加用户.
     *
     * @param form 用户表单对象
     * @return 用户编号 long
     * @index 3
     */
    @PostMapping
    public Long addUser(@RequestBody @Validated({Add.class}) UserForm form) {
        return 1L;
    }


    /**
     * 修改用户对象.
     *
     * @param form 用户表单对象
     * @return 是否成功 boolean
     * @index 2
     */
    @PutMapping
    public Boolean editUser(@RequestBody @Validated({Edit.class}) UserForm form) {
        return false;
    }


    /**
     * 删除用户.
     *
     * @param userId 用户编号
     * @return the user
     * @index 1
     */
    @DeleteMapping(value = "/{userId}")
    public Boolean removeUser(@PathVariable("userId") Long userId) {
        return false;
    }

    /**
     * 获取用户列表-分页.
     *
     * @param query 查询对象
     * @return the user page
     */
    @GetMapping
    public IPage<UserVO> queryUserPageList(UserQuery query) {
        return null;
    }
}
