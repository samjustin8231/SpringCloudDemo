package com.example.sb.springbootmybatis.util;

import com.example.sb.springbootmybatis.exception.DescribeException;
import com.example.sb.springbootmybatis.pojo.ExceptionEnum;
import com.example.sb.springbootmybatis.pojo.Result;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/8/25 23:10
 */
public class ResultUtil {

    /**
     * 返回成功，传入返回体具体出參
     *
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setStatus(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 自定义错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setStatus(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @param exceptionEnum
     * @return
     */
    public static Result error(ExceptionEnum exceptionEnum) {
        Result result = new Result();
        result.setStatus(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }


    public static Result error(DescribeException exception) {
        Result result = new Result();
        result.setStatus(exception.getCode());
        result.setMsg(exception.getMessage());
        result.setData(null);
        return result;
    }
}
