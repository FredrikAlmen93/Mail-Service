package se.iths.fredrik.mailservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Mail-Service application.
 *
 * <p>Configures the Spring Boot application and enables RabbitMQ
 * listener support for consuming messages.</p>
 */
@SpringBootApplication
@EnableRabbit
public class MailServiceApplication {

    /**
     * Starts the Mail-Service application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }

}
