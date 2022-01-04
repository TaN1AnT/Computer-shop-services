package com.onlinecomputershop.serviceorders.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public final class Order {
    private String products;
    private int price;
    private LocalDate order_date;
    private String description;
}
