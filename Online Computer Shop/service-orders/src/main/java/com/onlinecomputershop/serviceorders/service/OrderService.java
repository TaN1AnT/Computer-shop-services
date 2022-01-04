package com.onlinecomputershop.serviceorders.service;

import com.onlinecomputershop.serviceorders.api.dto.Device;
import com.onlinecomputershop.serviceorders.api.dto.OrderOut;
import com.onlinecomputershop.serviceorders.repo.OrderRepo;
import com.onlinecomputershop.serviceorders.repo.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public List<OrderOut> fetchAll(){
        List<Order> orders = orderRepo.findAll();

        List<OrderOut> ordersOut = new ArrayList<>();
        List<Device> devicesList;
        for (Order order : orders) {
            RestTemplate restTemplate = new RestTemplate();

            String products = order.getProducts();
            devicesList = new ArrayList<>();
            Device device1 = restTemplate.getForObject("http://localhost:8080/devices/" + products, Device.class);
            devicesList.add(device1);

            OrderOut orderOut = new OrderOut(order, devicesList);
            ordersOut.add(orderOut);

        }
        return ordersOut;
    }

    public OrderOut fetchOne(long id){
        final Optional<Order> maybeorder = orderRepo.findById(id);

        List<Device> devicesList;
        Order order;
        if(maybeorder.isPresent()){
            order = maybeorder.get();
            RestTemplate restTemplate = new RestTemplate();

            String products = order.getProducts();
            devicesList = new ArrayList<>();
            Device device1 = restTemplate.getForObject("http://localhost:8080/devices/1", Device.class);
            devicesList.add(device1);


            OrderOut orderOut = new OrderOut(order, devicesList);

            return orderOut;
        } else {
            throw new IllegalArgumentException("Invalid movie ID");
        }
    }

    public long create(String products, int price, LocalDate order_date, String description){
        final Order neworder = new Order(products, price, order_date, description);

        final Order savedorder = orderRepo.save(neworder);
        return savedorder.getId();
    }

    public long update(long id, com.onlinecomputershop.serviceorders.api.dto.Order order) throws IllegalArgumentException {
        final Optional<Order> maybeorder = orderRepo.findById(id);
        final String products = order.getProducts();
        final int price = order.getPrice();
        final LocalDate order_date = order.getOrder_date();
        final String description = order.getDescription();

        if (maybeorder.isPresent()) {
            final Order existedorder = maybeorder.get();
            if (products != null && !products.isBlank()) existedorder.setProducts(products);
            if (price > 0) existedorder.setPrice(price);
            if (order_date != null) existedorder.setOrder_date(order_date);
            if (description != null && !description.isBlank()) existedorder.setDescription(description);
            orderRepo.save(existedorder);
        } else throw new IllegalArgumentException("Invalid device ID");
        return id;
    }

    public void delete(long id) throws IllegalArgumentException {
        orderRepo.deleteById(id);
    }
}
