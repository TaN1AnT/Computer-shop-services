package com.onlinecomputershop.serviceorders.api.dto;

import com.onlinecomputershop.serviceorders.repo.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderOut {
    private Order order;
    private List<Device> devices;

    public OrderOut(Order orders, List<Device> devicesList) {
        this.order = orders;
        this.devices = devicesList;
    }

}
