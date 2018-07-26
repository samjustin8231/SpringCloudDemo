package com.example.sc.timedtaskdemo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/26 14:40
 */
@Component
public class SchedulerTask {

    private int count=0;

    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }

}
