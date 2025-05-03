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

        String subject2 = "Thank you For Contacting";
        String body2 = "Hi "+contactForm.getName() +",\n" + //
                        "\n" + //
                        "Thank you for contacting me through my portfolio website — http://portfolio-production-d593.up.railway.app/. I appreciate you taking the time to get in touch.\n" + //
                        "\n" + //
                        "I’ve received your message and will get back to you as soon as possible. In the meantime, feel free to explore more of my work or connect with me on LinkedIn or GitHub.\n" + //
                        "\n" + //
                        "Looking forward to speaking with you soon!\n" + //
                        "\n" + //
                        "Best regards,\n" + //
                        "Shrysth Shukla";
        emailService.sendEmail(contactForm.getEmail(), subject2, body2, "siddshrysth@gmail.com");
        return ResponseEntity.ok("email sent");
    } catch (Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }


}

}