package com.onlinecomputershop.serviceproducts.repo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "devices")
public final class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String type;
    private String brand;
    private int price;
    private LocalDate release_date;
    private String img_url;
    private String description;


    public Device(){
    }

    public Device(String name, String type, String brand, int price, LocalDate release_date, String img_url, String description) {
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.release_date = release_date;
        this.img_url = img_url;
        this.description = description;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
