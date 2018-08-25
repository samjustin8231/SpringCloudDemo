package com.example.sb.springbootmybatis.dao;

import com.example.sb.springbootmybatis.pojo.UserDO;
import com.example.sb.springbootmybatis.pojo.UserDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    long countByExample(UserDOExample example);

    int deleteByExample(UserDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    List<UserDO> selectByExample(UserDOExample example);

    UserDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDO record, @Param("example") UserDOExample example);

    int updateByExample(@Param("record") UserDO record, @Param("example") UserDOExample example);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}
