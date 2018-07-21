package com.example.sc.rabbitmqsender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes=RabbitmqsenderApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void testRabbitSendString() {
        helloSender.sendString();
    }

    @Test
    public void testRabbitSendUser() {
        helloSender.sendUserObject();
    }

    @Test
    public void testRabbitSendTopic() {
        helloSender.sendTopic();
    }
}
