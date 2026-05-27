package se.iths.fredrik.mailservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import se.iths.fredrik.mailservice.config.RabbitMQConfig;
import se.iths.fredrik.mailservice.dto.OrderEmailMessage;
import se.iths.fredrik.messenger.service.MailService;

@Service
@RequiredArgsConstructor
public class ConfirmationListener {
    private final MailService mailService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void receive(OrderEmailMessage message) {
        mailService.sendMail(
                message.getEmail(),
                message.getMessage()
        );
    }
}
