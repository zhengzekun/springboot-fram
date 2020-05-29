package com.howie.shirojwt.controller;

import com.howie.shirojwt.entity.User;
import com.howie.shirojwt.mapper.UserMapper;
import com.howie.shirojwt.model.ResultMap;
import com.howie.shirojwt.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * admin管理员角色可访问的接口
 *
 * @Author zekun.zheng@unionman.com.cn
 * @Description admin角色权限controller
 * @Date 2020-05-29
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    private final ResultMap resultMap;

    @Autowired
    public AdminController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 管理员获取用户信息
     * @return
     */
    @GetMapping("/getUsers")
    @RequiresRoles("admin")
    public ResultMap getUsers() {
        List<User> users = userService.getUsers();
        return resultMap.code(200).success().message(users);
    }

    /**
     * 封号操作
     */
    @PostMapping("/banUser")
    @RequiresRoles("admin")
    public ResultMap updatePassword(String username) {
        userService.banUser(username);
        return resultMap.success().code(200).message("成功封号！");
    }

    /**
     * 添加用户
     */
    @PostMapping("/addUser")
    @RequiresRoles("admin")
    public ResultMap addUser(User user) {
        User o = new User();
        o.setUsername("新增的");
        return resultMap.success().code(200).message(o);
    }
}
