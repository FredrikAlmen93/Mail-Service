package se.iths.fredrik.mailservice.dto;

import lombok.Data;

@Data
public class OrderItemDto {

    private String productName;
    private int quantity;
    private double price;
}
