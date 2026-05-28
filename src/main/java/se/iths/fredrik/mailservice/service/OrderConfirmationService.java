package se.iths.fredrik.mailservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OrderConfirmationService {
    private final JavaMailSender javaMailSender;

    public OrderConfirmationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(to);
        mailMessage.setSubject("Order Confirmation");
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }
}
