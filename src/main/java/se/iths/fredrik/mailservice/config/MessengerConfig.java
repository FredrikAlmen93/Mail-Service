package se.iths.fredrik.mailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import se.iths.fredrik.messenger.service.MailService;

@Configuration
public class MessengerConfig {

    @Bean
    public MailService mailService(JavaMailSender mailSender) {
        return new MailService(mailSender);
    }
}
