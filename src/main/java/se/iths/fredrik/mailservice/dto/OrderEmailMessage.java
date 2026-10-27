package se.iths.fredrik.mailservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an email message containing order confirmation details.
 *
 * <p>Stores the recipient's email address and the message content.
 * Validation constraints define the expected format and required values
 * for these fields.</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEmailMessage {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Message cannot be empty")
    private String message;

    /**
     * Checks wether the email address and message content are present and not blank.
     *
     * @return {@code true} if both fields contain non-blank values;
     *         {@code false} otherwise
     */
    public boolean isValid() {
        return email != null && !email.isBlank() &&
                message != null && !message.isBlank();
    }
}
