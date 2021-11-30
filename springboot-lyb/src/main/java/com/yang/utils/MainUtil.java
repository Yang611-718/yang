package com.yang.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MainUtil {

    @Autowired
    JavaMailSenderImpl mailSender;

    public  void sendCheckCodeEmail(String email,String content) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("验证码信息");
        message.setText(content);
        message.setTo(email);
        message.setFrom("948713975@qq.com");
        System.out.println(email);
        mailSender.send(message);
    }
}
