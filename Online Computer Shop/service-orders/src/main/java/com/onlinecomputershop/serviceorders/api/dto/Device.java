package com.onlinecomputershop.serviceorders.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Device {
    private long id;
    private String name;
    private String type;
    private String brand;
    private int price;
    private LocalDate release_date;
    private String img_url;
    private String description;
}
