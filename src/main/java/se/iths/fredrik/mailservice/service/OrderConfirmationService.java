package se.iths.fredrik.mailservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.fredrik.mailservice.dto.OrderEmailMessage;
import se.iths.fredrik.mailservice.dto.OrderItemDto;
import se.iths.fredrik.messenger.service.MailService;

@Service
public class OrderConfirmationService {

    private final MailService mailService;

    public OrderConfirmationService(MailService mailService) {
        this.mailService = mailService;
    }

    public void sendOrderConfirmation(String to, String message) {
        mailService.sendMail(to, message);
    }
}
