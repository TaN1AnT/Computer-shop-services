package com.onlinecomputershop.serviceproducts.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Type {
    private long id;
    private String name;
    private String description;

}