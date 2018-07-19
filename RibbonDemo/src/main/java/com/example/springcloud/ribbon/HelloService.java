package com.example.springcloud.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/19 17:17
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    //创建了熔断器的功能，当访问的service-a服务挂了的时候，自动返回hiError的结果
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://service-a/hi?name=" + name, String.class);
    }

    /**
     * 发生熔断时，调用该方法
     * @param name
     * @return
     */
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
