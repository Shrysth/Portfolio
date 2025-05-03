package com.portfolio.Shrysth.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String body, String replyTo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("siddshrysth@gmail.com");
        message.setTo(to);
    
        message.setSubject(subject);
        message.setText(body);
        if (replyTo != null && !replyTo.isEmpty()) {
            message.setReplyTo(replyTo);
        }
    
        emailSender.send(message);
    }
    
    
}
