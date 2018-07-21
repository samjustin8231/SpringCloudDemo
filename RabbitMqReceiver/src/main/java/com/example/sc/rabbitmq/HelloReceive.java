package com.example.sc.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloReceive {

    @RabbitListener(queues="queue")    //监听器监听指定的Queue, 注意：需要在rabbitmq控制台先添加name为queue的队列
    public void processC(String str) {
        System.out.println("Receive:"+str);
    }


    @RabbitListener(queues="queue")    //监听器监听指定的Queue
    public void process1(User user) {    //用User作为参数
        System.out.println("Receive1:"+user);
    }

    @RabbitListener(queues="topic.message")    //监听器监听指定的Queue
    public void process1(String str) {
        System.out.println("message:"+str);
    }
    @RabbitListener(queues="topic.messages")    //监听器监听指定的Queue
    public void process2(String str) {
        System.out.println("messages:"+str);
    }
}
