package com.onlinecomputershop.serviceorders.repo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public final class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String products;
    private int price;
    private LocalDate order_date;
    private String description;


    public Order(){
    }

    public Order(String products, int price, LocalDate order_date, String description) {
        this.products = products;
        this.price = price;
        this.order_date = order_date;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
