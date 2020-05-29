package com.howie.shirojwt.entity;

import lombok.Data;

/**
 * 用户
 */
@Data
public class User{
    private int id;
    private String username;
    private String password;
    private String role;
    private String permission;
    private boolean ban;
}
