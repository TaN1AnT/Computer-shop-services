package com.onlinecomputershop.serviceproducts.service;

import com.onlinecomputershop.serviceproducts.api.dto.Brand;
import com.onlinecomputershop.serviceproducts.api.dto.DeviceOut;
import com.onlinecomputershop.serviceproducts.api.dto.Type;
import com.onlinecomputershop.serviceproducts.repo.DeviceRepo;
import com.onlinecomputershop.serviceproducts.repo.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeviceService {

    private final DeviceRepo devicerepo;

    public List<DeviceOut> fetchAll(){
        List<Device> devices = devicerepo.findAll();

        List<DeviceOut> devicesOut = new ArrayList<>();
        List<Brand> brandList;
        List<Type> typeList;
        for (Device device : devices) {
            RestTemplate restTemplate = new RestTemplate();

            String brand = device.getBrand();
            brandList = new ArrayList<>();
            Brand brand1 = restTemplate.getForObject("http://192.168.49.2:31002/brands/" + brand, Brand.class);
            brandList.add(brand1);

            String type = device.getType();
            typeList = new ArrayList<>();
            Type type1 = restTemplate.getForObject("http://192.168.49.2:31003/types/" + type, Type.class);
            typeList.add(type1);
            DeviceOut deviceOut = new DeviceOut(device, brandList, typeList);
            devicesOut.add(deviceOut);

        }
        return devicesOut;

    }

    public DeviceOut fetchOne(long id){
        final Optional<Device> maybedevice = devicerepo.findById(id);

        Device device;
        if(maybedevice.isPresent()){
            device = maybedevice.get();
            RestTemplate restTemplate = new RestTemplate();

            List<Brand> brandList;
            String brand = device.getBrand();
            brandList = new ArrayList<>();
            Brand brand1 = restTemplate.getForObject("http://192.168.49.2:31002/brands/" + brand, Brand.class);
            brandList.add(brand1);

            List<Type> typeList;
            String type = device.getType();
            typeList = new ArrayList<>();
            Type type1 = restTemplate.getForObject("http://192.168.49.2:31003/types/" + type, Type.class);
            typeList.add(type1);
            DeviceOut deviceOut = new DeviceOut(device, brandList, typeList);

            return deviceOut;
        } else {
            throw new IllegalArgumentException("Invalid movie ID");
        }
    }

    public long create(String name, String type, String brand, int price, LocalDate release_date, String img_url, String description){
        final Device newdevice = new Device(name, type, brand, price, release_date, img_url, description);

        final Device saveddevice = devicerepo.save(newdevice);
        return saveddevice.getId();
    }

    public long update(long id, com.onlinecomputershop.serviceproducts.api.dto.Device device) throws IllegalArgumentException {
        final Optional<Device> maybedevice = devicerepo.findById(id);
        final String brand = device.getBrand();
        final String img_url = device.getImg_url();
        final String name = device.getName();
        final int price = device.getPrice();
        final LocalDate release_date = device.getRelease_date();
        final String type = device.getType();
        final String description = device.getDescription();

        if (maybedevice.isPresent()) {
            final Device existeddevice = maybedevice.get();
            if (name != null && !name.isBlank()) existeddevice.setName(name);
            if (type != null && !type.isBlank()) existeddevice.setType(type);
            if (brand != null && !brand.isBlank()) existeddevice.setBrand(brand);
            if (price > 0) existeddevice.setPrice(price);
            if (release_date != null) existeddevice.setRelease_date(release_date);
            if (img_url != null && !img_url.isBlank()) existeddevice.setImg_url(img_url);
            if (description != null && !description.isBlank()) existeddevice.setDescription(description);
            devicerepo.save(existeddevice);
        } else throw new IllegalArgumentException("Invalid device ID");
        return id;
    }

    public void delete(long id) throws IllegalArgumentException {
        devicerepo.deleteById(id);
    }
}
