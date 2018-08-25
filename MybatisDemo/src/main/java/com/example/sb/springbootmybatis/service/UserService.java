package com.example.sb.springbootmybatis.service;

import com.example.sb.springbootmybatis.pojo.UserDO;
import com.example.sb.springbootmybatis.pojo.UserDOExample;

import java.util.List;

public interface UserService {
    public UserDO getUserById(int userId);

    boolean addUser(UserDO record);

    List<UserDO> selectByExample(UserDOExample userDOExample);
}
