package com.example.sb.springbootmybatis.dao;

import com.example.sb.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 使用注解方式映射sql
 */
@Mapper
public interface UserMapperForAnotation {

    @Select("SELECT * FROM T_USER WHERE PHONE = #{phone}")
    User findUserByPhone(@Param("phone") String phone);

    @Insert("INSERT INTO T_USER(USER_NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
    int insert(@Param("name") String name, @Param("password") String password, @Param("phone") String phone);

}
