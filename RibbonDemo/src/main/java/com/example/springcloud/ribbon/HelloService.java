package com.example.springcloud.ribbon;

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

    public String hiService(String name) {
        return restTemplate.getForObject("http://service-a/hi?name=" + name, String.class);
    }


}
