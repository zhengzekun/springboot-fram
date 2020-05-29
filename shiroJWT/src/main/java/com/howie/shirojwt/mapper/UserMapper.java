package com.howie.shirojwt.mapper;

import com.howie.shirojwt.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @Author zekun.zheng@unionman.com.cn
 * @Description
 * @Date 2020-05-29
 */
@Repository
public interface UserMapper {

    User findByUsername(String username);

    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 根据用户名和密码获取用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    /**
     * 获得密码
     * @param username 用户名
     */
    String getPassword(String username);

    /**
     * 获得角色权限
     * @param username 用户名
     * @return user/admin
     */
    String getRole(String username);

    /**
     * 修改密码
     */
    void updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);

    /**
     * 获得存在的用户
     */
    List<User> getUsers();

    /**
     * 封号
     */
    void banUser(String username);

    /**
     * 检查用户状态
     */
    int checkUserBanStatus(String username);

    /**
     * 获得用户角色默认的权限
     */
    String getRolePermission(String username);

    /**
     * 获得用户的权限
     */
    String getPermission(String username);
}
