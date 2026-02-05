package com.joaopedroamaral.taskManager.Service;

import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService (JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, String token){
        String link = "http://localhost:8080/api/register/authenticateEmail?token="
                + token
                + "&email=" + email;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verify your email");
        message.setText("Welcome\n\nPlease, click in the link bellow to verify your email:\n" + link);

        mailSender.send(message);
    }
}
