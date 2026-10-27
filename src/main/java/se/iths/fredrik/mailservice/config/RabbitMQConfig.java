package se.iths.fredrik.mailservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;

/**
 * Configuration class for setting up RabbitMQ messaging.
 *
 * <p>Defines the queue, message converter and RabbitTemplate
 * used for communication through RabbitMQ.</p>
 */
@Configuration
public class RabbitMQConfig {

    /**
     * The name of the queue used for order confirmation messages.
     */
    public static final String ORDER_QUEUE = "confirmation-queue";

    /**
     * Creates a durable queue for order confirmation messages.
     *
     * @return the configured order confirmation queue
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE, true);
    }

    /**
     * Creates a message converter for handling JSON serialization/deserialization.
     *
     * @return the configured {@link MessageConverter} instance
     */
    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    /**
     * Creates a RabbitTemplate for interacting with RabbitMQ.
     *
     * @param connectionFactory the connection factory for creating RabbitMQ connections
     * @return the configured {@link RabbitTemplate} instance
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
