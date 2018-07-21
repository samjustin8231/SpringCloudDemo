package com.example.sb.springbootmybatis.service;

import com.example.sb.springbootmybatis.pojo.User;

public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);
}
