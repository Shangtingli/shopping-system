package com.springboot.project.onlineShop.service;

public interface EmailService {
    void send(String to, String subject, String text);
}
