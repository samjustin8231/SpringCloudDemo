package com.example.sb.springbootmybatis.controller;

import com.alibaba.fastjson.JSON;
import com.example.sb.springbootmybatis.exception.DescribeException;
import com.example.sb.springbootmybatis.pojo.Result;
import com.example.sb.springbootmybatis.pojo.UserDO;
import com.example.sb.springbootmybatis.pojo.UserDOExample;
import com.example.sb.springbootmybatis.reposity.UserRepository;
import com.example.sb.springbootmybatis.service.UserService;
import com.example.sb.springbootmybatis.util.RedisUtil;
import com.example.sb.springbootmybatis.util.ResultUtil;
import com.example.sb.springbootmybatis.util.StringConstant;
import lombok.NonNull;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private UserRepository userRepository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;


    @RequestMapping(value = "/redis/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserByIdFromRedis(@RequestParam("id") String userId) {
        UserDOExample userDOExample = new UserDOExample();
        if (StringUtils.isEmpty(userId)) {
            return ResultUtil.error(new DescribeException("userId不存在！", -1));
        }
        //先从redis取，如果有，直接返回
        Object userValue = redisUtil.get(StringConstant.PRE_USER_INFO + userId);
        if (userValue == null) {
            //redis没有，则取db中查，并更新到redis
            UserDOExample.Criteria userDOExampleCriteria = userDOExample.createCriteria();
            int userIdInt = Integer.parseInt(userId);
            userDOExampleCriteria.andIdEqualTo(userIdInt);
            List<UserDO> userDOList = userService.selectByExample(userDOExample);
            if (userDOList != null && !userDOList.isEmpty()) {
                UserDO userDOResult = userDOList.get(0);
                redisUtil.set(StringConstant.PRE_USER_INFO + userId, JSON.toJSONString(userDOResult));
                return ResultUtil.success(userDOResult);
            } else {
                return ResultUtil.error(new DescribeException("未找到数据！", -1));
            }
        } else {
            return ResultUtil.success(userValue);
        }
    }


    @RequestMapping(value = "/es/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Result addUserToEs(@RequestBody @NonNull UserDO userDO) {
        if (userDO.getId() == null) {
            return ResultUtil.error(new DescribeException("userId不能为空！", -1));
        }
        //mysql中没有则添加
        UserDOExample userDOExample = new UserDOExample();
        UserDOExample.Criteria userDOExampleCriteria = userDOExample.createCriteria();
        userDOExampleCriteria.andIdEqualTo(userDO.getId());
        List<UserDO> userDOList = userService.selectByExample(userDOExample);
        if (userDOList != null && !userDOList.isEmpty()) {
            return ResultUtil.error(new DescribeException("该id在mysql已经存在", -1));
        } else {
            //添加到mysql
            boolean isAdded = userService.addUser(userDO);
        }

        //es中没有则添加
        UserDO userResult = userRepository.findOne(new Long(userDO.getId()));
        if (userResult != null) {
            return ResultUtil.error(new DescribeException("该id在es中已经存在", -1));
        } else {
            //添加到es
            UserDO userAdded = userRepository.save(userDO);
        }
        return ResultUtil.success(userDO);
    }

    @RequestMapping(value = "/es/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserByIdFromEs(@RequestParam("id") String userId) {
        UserDOExample userDOExample = new UserDOExample();
        if (StringUtils.isEmpty(userId)) {
            return ResultUtil.error(new DescribeException("userId不存在！", -1));
        }
        //先从es取，如果有，直接返回
        UserDO userDO = userRepository.findOne(Long.parseLong(userId));
        if (userDO == null) {
            //es没有，则取db中查，并更新到es
            UserDOExample.Criteria userDOExampleCriteria = userDOExample.createCriteria();
            int userIdInt = Integer.parseInt(userId);
            userDOExampleCriteria.andIdEqualTo(userIdInt);
            List<UserDO> userDOList = userService.selectByExample(userDOExample);
            if (userDOList != null && !userDOList.isEmpty()) {
                UserDO userDOResult = userDOList.get(0);
                userRepository.save(userDOResult);
                return ResultUtil.success(userDOResult);
            } else {
                return ResultUtil.error(new DescribeException("未找到数据！", -1));
            }
        } else {
            return ResultUtil.success(userDO);
        }
    }
}
