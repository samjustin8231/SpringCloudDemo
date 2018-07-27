package com.example.sc.maildemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/27 10:02
 */

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    JavaMailSender mailSender;

    @RequestMapping("sendemail")
    public Object sendEmail()
    {
        return null;
    }

}
