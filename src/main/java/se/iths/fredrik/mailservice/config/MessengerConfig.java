package se.iths.fredrik.mailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import se.iths.fredrik.messenger.service.MailService;

/**
 * Configuration class for setting up the MailService bean.
 *
 * <p>Defines Spring beans required for sending emails through the application's mail infrastructure.</p>
 */
@Configuration
public class MessengerConfig {

    /**
     * Creates a {link MailService} bean for sending emails.
     *
     * <p>Spring injects the configured {@link JavaMailSender} into the service.</p>
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
