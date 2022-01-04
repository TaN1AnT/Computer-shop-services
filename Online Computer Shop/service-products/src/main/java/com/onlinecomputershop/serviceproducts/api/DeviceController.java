package com.onlinecomputershop.serviceproducts.api;

import com.onlinecomputershop.serviceproducts.api.dto.DeviceOut;
import com.onlinecomputershop.serviceproducts.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/devices")
public final class DeviceController {

    private final DeviceService deviceService;
    @GetMapping
    public ResponseEntity<List<DeviceOut>> index(){
        final List<DeviceOut> devices = deviceService.fetchAll();
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceOut> show(@PathVariable long id) {
        try{
            final DeviceOut device = deviceService.fetchOne(id);
            return ResponseEntity.ok(device);

        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.onlinecomputershop.serviceproducts.api.dto.Device device) {
        final String brand = device.getBrand();
        final String img_url = device.getImg_url();
        final String name = device.getName();
        final int price = device.getPrice();
        final LocalDate release_date = device.getRelease_date();
        final String type = device.getType();
        final String description = device.getDescription();

        final long id = deviceService.create(name, type, brand, price, release_date, img_url, description);
        final String location = String.format("/devices/%d", id);

        return ResponseEntity.created(URI.create(location)).build();

    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.onlinecomputershop.serviceproducts.api.dto.Device device) {
        final String brand = device.getBrand();
        final String img_url = device.getImg_url();
        final String name = device.getName();
        final int price = device.getPrice();
        final LocalDate release_date = device.getRelease_date();
        final String type = device.getType();
        final String description = device.getDescription();

        try{
            deviceService.update(id, device);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        deviceService.delete(id);

        return ResponseEntity.noContent().build();

    }

}
