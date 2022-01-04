package com.onlinecomputershop.serviceproducts.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public final class Device {
    private String name;
    private String type;
    private String brand;
    private int price;
    private LocalDate release_date;
    private String img_url;
    private String description;
}
