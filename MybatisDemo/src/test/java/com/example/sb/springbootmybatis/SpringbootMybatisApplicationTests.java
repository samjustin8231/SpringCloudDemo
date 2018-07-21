package com.example.sb.springbootmybatis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertAndQuery(){

        userMapper.insert("winterchen", "123456", "12345678910");
        User u = userMapper.findUserByPhone("12345678910");
        Assert.assertEquals("winterchen", u.getName());
    }

    @Test
    @Transactional
    public void testTransaction(){

        userMapper.insert("张三", "123456", "18600000000");
        int a = 1/0;
        userMapper.insert("李四", "123456", "13500000000");
        User u = userMapper.findUserByPhone("12345678910");
        Assert.assertEquals("winterchen", u.getName());
    }
}
