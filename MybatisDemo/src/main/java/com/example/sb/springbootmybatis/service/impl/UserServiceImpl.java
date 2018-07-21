package com.example.sb.springbootmybatis.service.impl;

import com.example.sb.springbootmybatis.pojo.User;
import com.example.sb.springbootmybatis.dao.UserDao;
import com.example.sb.springbootmybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public boolean addUser(User record){
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
