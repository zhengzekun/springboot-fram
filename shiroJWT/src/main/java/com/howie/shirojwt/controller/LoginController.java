package com.howie.shirojwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.howie.shirojwt.entity.User;
import com.howie.shirojwt.model.ResultMap;
import com.howie.shirojwt.service.UserService;
import com.howie.shirojwt.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * 用户登录controller
 *
 * @Author zekun.zheng@unionman.com.cn
 * @Description
 * @Date 2020-05-29
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    private final ResultMap resultMap;

    @Autowired
    public LoginController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 用户登录
     * @param o
     * @return
     */
    @PostMapping("/login")
    public ResultMap login(@RequestBody User o) {
        String realPassword = userService.getPassword(o.getUsername());
        if (realPassword == null) {
            return resultMap.fail().code(401).message("用户名错误");
        } else if (!realPassword.equals(o.getPassword())) {
            return resultMap.fail().code(401).message("密码错误");
        } else {
            User user = userService.findByUsernameAndPassword(o.getUsername(), realPassword);
            return resultMap.success().code(200).message(JWTUtil.createToken(o.getUsername(), user.getId()));
        }
    }

    /**
     * 未认证接口
     * @param message
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(path = "/unauthorized/{message}")
    public ResultMap unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return resultMap.success().code(401).message(message);
    }

    /**
     * 根据token返回用户信息
     * @param token
     * @return
     */
    @GetMapping("/tokenLogin")
    public ResultMap tokenLogin(String token) {
        DecodedJWT jwt = JWT.decode(token);
        int id = jwt.getClaim("id").asInt();
        User user = userService.findUserById(id);
        return resultMap.success().code(200).message(user);
    }
}
