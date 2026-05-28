package se.iths.fredrik.mailservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    @NotBlank(message = "")
    private String productName;

    @Min(value = 1, message = "Quantity must be atleast 1")
    private int quantity;

    @Min(value = 0, message = "Price cannot be negative")
    private double price;
}
