package com.example.sc.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloReceive {

    @RabbitListener(queues="queue")    //监听器监听指定的Queue, 注意：需要在rabbitmq控制台先添加name为queue的队列
    public void processC(String str) {
        System.out.println("Receive:"+str);
    }

}
