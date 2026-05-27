package se.iths.fredrik.mailservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import se.iths.fredrik.mailservice.config.RabbitMQConfig;
import se.iths.fredrik.mailservice.dto.OrderEmailMessage;
import se.iths.fredrik.mailservice.service.OrderConfirmationService;

@Service
@RequiredArgsConstructor
public class ConfirmationListener {
    private final OrderConfirmationService orderConfirmationService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void receive(OrderEmailMessage message) {
        orderConfirmationService.sendOrderConfirmation(message);
    }
}
