package com.example.sc.rabbitmqsender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate template;

    /**
     * 发送字符串
     */
    public void sendString() {
        template.convertAndSend("queue","hello,rabbit~");
    }

}
