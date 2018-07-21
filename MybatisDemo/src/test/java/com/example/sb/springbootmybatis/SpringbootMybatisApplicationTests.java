package com.example.sb.springbootmybatis;

import com.example.sb.springbootmybatis.dao.UserMapperForAnotation;
import com.example.sb.springbootmybatis.pojo.User;
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
    private UserMapperForAnotation userMapperForAnotation;

    @Test
    public void testInsert(){

        int count = userMapperForAnotation.insert("sam", "123456", "17717372410");
        Assert.assertEquals(1, count);
    }

    @Test
    public void testQuery(){
        User u = userMapperForAnotation.findUserByPhone("12345678910");
        Assert.assertEquals("winterchen", u.getUserName());
    }

    @Test
    @Transactional
    public void testTransaction(){

        userMapperForAnotation.insert("张三", "123456", "18600000000");
        int a = 1/0;
        userMapperForAnotation.insert("李四", "123456", "13500000000");
        User u = userMapperForAnotation.findUserByPhone("12345678910");
        Assert.assertEquals("winterchen", u.getUserName());
    }
}
