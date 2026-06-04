package se.iths.fredrik.mailservice.service;

import org.springframework.stereotype.Service;

@Service
public class EmailFormattingService {

    public String formatOrderConfirmation(String orderMessage) {
        return "Order Confirmation,\n\n" +
                orderMessage +
                "\nKindest regards," +
                "\n\nThe Webshop Team";
    }
}
