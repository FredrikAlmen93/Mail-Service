package se.iths.fredrik.mailservice.consumer;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import se.iths.fredrik.mailservice.config.RabbitMQConfig;
import se.iths.fredrik.mailservice.dto.OrderEmailMessage;
import se.iths.fredrik.mailservice.service.EmailFormattingService;
import se.iths.fredrik.messenger.service.MailService;

@Component
public class ConfirmationConsumer {

    private final MailService mailService;
    private final EmailFormattingService emailFormattingService;

    public ConfirmationConsumer(MailService mailService, EmailFormattingService emailFormattingService) {
        this.mailService = mailService;
        this.emailFormattingService = emailFormattingService;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void receive(OrderEmailMessage message) {
        if(message == null || !message.isValid()){
            return;
        }
        try {
            String formattedMessage = emailFormattingService.formatOrderConfirmation(message.getMessage());
            mailService.sendMail(
                    message.getEmail(),
                    formattedMessage
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to send confirmation email", e);
        }
    }
}
