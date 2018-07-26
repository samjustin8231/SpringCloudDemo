package com.example.sc.timedtaskdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TimedTaskDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimedTaskDemoApplication.class, args);
    }
}
