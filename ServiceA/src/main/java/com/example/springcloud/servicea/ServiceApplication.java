package com.example.springcloud.servicea;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/19 15:22
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ServiceApplication {

    @Value("${spring.application.name}")
    private String name;
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @RequestMapping("/hi")
    public String hi(@RequestParam String id) {
        return "hi, " + id + ", " + name + ":" + port;
    }

}
