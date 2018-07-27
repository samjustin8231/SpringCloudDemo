package com.neo.shirodemo.sevice;


import com.neo.shirodemo.entity.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}