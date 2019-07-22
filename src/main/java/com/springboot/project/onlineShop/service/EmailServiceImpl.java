package com.springboot.project.onlineShop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService{
    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String subject, String body) {
        log.info("Trying to send confirmation Email to {}", to);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);//true indicates multipart message
            helper.setFrom(from) ;// <--- THIS IS IMPORTANT

            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body, true);//true indicates body is html
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}