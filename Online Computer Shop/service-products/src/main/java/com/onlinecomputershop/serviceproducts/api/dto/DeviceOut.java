package com.onlinecomputershop.serviceproducts.api.dto;

import com.onlinecomputershop.serviceproducts.repo.model.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DeviceOut {
    private Device device;
    private List<Brand> brand;
    private List<Type> type;

    public DeviceOut(Device device, List<Brand> brandList, List<Type> typeList) {
        this.device = device;
        this.brand = brandList;
        this.type = typeList;
    }
}
