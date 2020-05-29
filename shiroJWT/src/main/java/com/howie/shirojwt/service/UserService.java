package com.howie.shirojwt.service;

import com.howie.shirojwt.entity.User;
import com.howie.shirojwt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author : zekun.zheng@unionman.com.cn
 * @date: 2020-05-29
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户ID获取用户信息
     * @param id
     * @return
     */
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    /**
     * 根据用户名获取密码
     * @param username
     * @return
     */
    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }

    /**
     * 根据用户名和密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    public User findByUsernameAndPassword(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }

    /**
     * 管理员获取用户信息
     * @return
     */
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    /**
     * 禁用用户
     * @param username
     */
    public void banUser(String username) {
        userMapper.banUser(username);
    }
}
