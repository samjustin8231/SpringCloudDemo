package com.example.sc.maildemo;

import com.example.sc.maildemo.config.EmailConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailDemoApplicationTests {
    @Autowired
    EmailConfig mc;
    @Test
    public void testEmailConfig(){
        MyEmail email = new MyEmail();
        email.setReceiver("sam8231@163.com");
        email.setContent("welcome ");
        email.setSubject("test");
        mc.sendSimpleMail(email);
        System.out.println("successful to send message");
    }

}
