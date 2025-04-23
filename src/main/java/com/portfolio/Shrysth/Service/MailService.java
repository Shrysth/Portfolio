package com.portfolio.Shrysth.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactMail(String name, String email, String messageText) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("your_email@gmail.com"); // Replace with your actual email
        helper.setSubject("New Contact Message from Portfolio");
        helper.setText("Name: " + name + "\nEmail: " + email + "\n\nMessage:\n" + messageText);

        mailSender.send(message);
    }
}
