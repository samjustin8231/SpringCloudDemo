package com.example.sc.multidatasource.mapper.test1;

import com.example.sc.multidatasource.entity.UserEntity;

import java.util.List;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/27 10:55
 */
public interface User1Mapper {
    List<UserEntity> getAll();
    UserEntity getOne(Long id);
    void insert(UserEntity user);
    void update(UserEntity user);
    void delete(Long id);
}
