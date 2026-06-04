package se.iths.fredrik.mailservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEmailMessage {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Message cannot be empty")
    private String message;

    public boolean isValid() {
        return email != null && !email.isBlank() &&
                message != null && !message.isBlank();
    }
}
