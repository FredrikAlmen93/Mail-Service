package se.iths.fredrik.mailservice.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import se.iths.fredrik.mailservice.config.RabbitMQConfig;
import se.iths.fredrik.mailservice.dto.OrderEmailMessage;
import se.iths.fredrik.messenger.service.MailService;

@Service
public class ConfirmationConsumer {
    private final MailService mailService;

    public ConfirmationConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void receive(OrderEmailMessage message) {
        mailService.sendMail(
                message.getEmail(),
                message.getMessage()
        );

        System.out.println("Received and sent message");
    }
}
