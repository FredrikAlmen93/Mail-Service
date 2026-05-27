package se.iths.fredrik.mailservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.fredrik.mailservice.dto.OrderEmailMessage;
import se.iths.fredrik.mailservice.dto.OrderItemDto;
import se.iths.fredrik.messenger.service.MailService;

@Service
@RequiredArgsConstructor
public class OrderConfirmationService {

    private final MailService mailService;

    public void sendOrderConfirmation(OrderEmailMessage order) {
        StringBuilder body = new StringBuilder();

        body.append("Order Confirmation\n");
        body.append("Date: ").append(order.getOrderDate()).append("\n");

        for(OrderItemDto item : order.getItems()) {
            body.append(item.getProductName())
                    .append(" * ")
                    .append(item.getQuantity())
                    .append(" = ")
                    .append(item.getPrice() * item.getQuantity())
                    .append("\n");
        }

        body.append("\nTotal: ").append(order.getTotalPrice());

        mailService.sendMail(
                order.getCustomerEmail(),
                "Your order confirmation"
        );
    }
}
