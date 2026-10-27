package se.iths.fredrik.mailservice.consumer;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import se.iths.fredrik.mailservice.config.RabbitMQConfig;
import se.iths.fredrik.mailservice.dto.OrderEmailMessage;
import se.iths.fredrik.mailservice.service.EmailFormattingService;
import se.iths.fredrik.messenger.service.MailService;

/**
 * Consumes order confirmation messages from RabbitMQ.
 *
 * <p>Validates incoming messages, formats the email content and
 * delegates email delivery to {@link MailService}</p>
 */
@Component
public class ConfirmationConsumer {

    private final MailService mailService;
    private final EmailFormattingService emailFormattingService;

    /**
     * Creates a consumer with the services required to process order confirmation emails.
     *
     * @param mailService the service responsible for sending emails
     * @param emailFormattingService the service responsible for formatting email content
     */
    public ConfirmationConsumer(MailService mailService, EmailFormattingService emailFormattingService) {
        this.mailService = mailService;
        this.emailFormattingService = emailFormattingService;
    }

    /**
     * Processes incoming order confirmation messages from the RabbitMQ queue.
     *
     * <p>Invalid or null messages are ignored. Valid messages are formatted and
     * passed to {@link MailService} for email delivery.</p>
     *
     * @param message the order confirmation message received from the RabbitMQ queue
     * @throws RuntimeException if an exception occurs while formatting or sending the confirmation email
     */
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
