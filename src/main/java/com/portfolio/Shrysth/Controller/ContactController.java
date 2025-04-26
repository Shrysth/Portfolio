package com.portfolio.Shrysth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.Shrysth.Contactform.ContactForm;
import com.portfolio.Shrysth.Service.EmailService;

@RestController
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/contact")
public ResponseEntity<String> sendContactEmail(@ModelAttribute ContactForm contactForm) {
    try {
        String subject = "Contact Form Submission: " + contactForm.getSubject();
        String body = "Name: " + contactForm.getName() + "\n" +
                      "Email: " + contactForm.getEmail() + "\n" +
                      "Message: " + contactForm.getMessage();
    
        // Call updated service method with reply-to address
        emailService.sendEmail("shrysthshukla@yahoo.com", subject, body, contactForm.getEmail());

        return ResponseEntity.ok("email sent");
    } catch (Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }


}

}