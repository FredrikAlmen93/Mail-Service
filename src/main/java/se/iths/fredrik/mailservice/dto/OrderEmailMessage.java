package se.iths.fredrik.mailservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderEmailMessage {

    private String customerEmail;
    private List<OrderItemDto> items;
    private double totalPrice;
    private LocalDateTime orderDate;
}
