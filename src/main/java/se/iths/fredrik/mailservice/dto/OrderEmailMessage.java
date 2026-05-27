package se.iths.fredrik.mailservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class OrderEmailMessage {
    private String email;
    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
