package com.example.sb.springbootmybatis.service.impl;

import com.example.sb.springbootmybatis.dao.UserDao;
import com.example.sb.springbootmybatis.pojo.UserDO;
import com.example.sb.springbootmybatis.pojo.UserDOExample;
import com.example.sb.springbootmybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    public UserDO getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public boolean addUser(UserDO record) {
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<UserDO> selectByExample(UserDOExample userDOExample) {
        return userDao.selectByExample(userDOExample);
    }

}
