package com.example.springcloud.feign;

import org.springframework.stereotype.Component;

/**
 * 类的实现描述：发生熔断时，执行该方法
 *
 * @author sunyajun 2018/7/19 17:51
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
