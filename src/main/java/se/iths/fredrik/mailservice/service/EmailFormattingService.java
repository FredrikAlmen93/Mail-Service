package se.iths.fredrik.mailservice.service;

import org.springframework.stereotype.Service;

/**
 * Formats the content of order confirmation emails.
 *
 * <p>Provides a consistent greeting and closing around the
 * order information included in the email.</p>
 */
@Service
public class EmailFormattingService {

    /**
     * Format and order confirmation with a greeting and a closing signature.
     *
     * @param orderMessage the order information to include in the email
     * @return the formatted order confirmation message
     */
    public String formatOrderConfirmation(String orderMessage) {
        return "Order Confirmation,\n\n" +
                orderMessage +
                "\nKindest regards," +
                "\n\nThe Webshop Team";
    }
}
