package com.onlinecomputershop.serviceorders.api;


import com.onlinecomputershop.serviceorders.api.dto.OrderOut;
import com.onlinecomputershop.serviceorders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public final class OrderController {

    private final OrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderOut>> index(){
        final List<OrderOut> orders = orderService.fetchAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderOut> show(@PathVariable long id) {
        try{
            final OrderOut order = orderService.fetchOne(id);
            return ResponseEntity.ok(order);

        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.onlinecomputershop.serviceorders.api.dto.Order order) {
        final String products = order.getProducts();
        final int price = order.getPrice();
        final LocalDate order_date = order.getOrder_date();
        final String description = order.getDescription();

        final long id = orderService.create(products, price, order_date, description);
        final String location = String.format("/devices/%d", id);

        return ResponseEntity.created(URI.create(location)).build();

    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.onlinecomputershop.serviceorders.api.dto.Order order) {
        final String products = order.getProducts();
        final int price = order.getPrice();
        final LocalDate order_date = order.getOrder_date();
        final String description = order.getDescription();

        try{
            orderService.update(id, order);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        orderService.delete(id);

        return ResponseEntity.noContent().build();

    }

}
