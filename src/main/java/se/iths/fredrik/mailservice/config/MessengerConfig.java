package se.iths.fredrik.mailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import se.iths.fredrik.messenger.service.MailService;

/**
 * Configures the mail service used by the application.
 *
 * <p>Registers a {@link MailService} bean that uses
 * Spring's {@link JavaMailSender} to send emails.</p>
 */
@Configuration
public class MessengerConfig {

    /**
     * Creates a {@link MailService} bean for sending emails.
     *
     * @param mailSender the mail sender bean used for sending emails
     * @return a configured {@link MailService} instance
     */
    @Bean
    public MailService mailService(JavaMailSender mailSender)
    {

        return new MailService(mailSender);
    }
}
