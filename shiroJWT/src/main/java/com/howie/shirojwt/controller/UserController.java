package com.howie.shirojwt.controller;

import com.howie.shirojwt.entity.User;
import com.howie.shirojwt.model.ResultMap;
import com.howie.shirojwt.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 已登录用户可访问的接口
 *
 * @Author zekun.zheng@unionman.com.cn
 * @Description user角色权限controller
 * @Date 2020-05-29
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private final ResultMap resultMap;

    @Autowired
    public UserController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 根据ID获取用户信息
     * @param
     * @return
     */
    @GetMapping("/getUser")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public ResultMap getUser() {
        User user = new User();
        user.setUsername("vip");
        return resultMap.success().code(200).message(user);
    }

    /**
     * 获取信息
     * 拥有 user, admin 角色的用户可以访问下面的页面
     */
    @GetMapping("/getM")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap getMessage() {
        User user = new User();
        user.setUsername("user");
        return resultMap.success().code(200).message(user);
    }

//    @PostMapping("/updatePassword")
//    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
//    public ResultMap updatePassword(String username, String oldPassword, String newPassword) {
//        String dataBasePassword = userMapper.getPassword(username);
//        if (dataBasePassword.equals(oldPassword)) {
//            userMapper.updatePassword(username, newPassword);
//        } else {
//            return resultMap.fail().message("密码错误！");
//        }
//        return resultMap.success().code(200).message("成功获得信息！");
//    }

}
