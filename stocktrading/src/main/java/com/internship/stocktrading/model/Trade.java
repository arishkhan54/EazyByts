package com.internship.stocktrading.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Stock stock;

    private String type;
    private int quantity;
    private double price;
    private LocalDateTime time;

    public Trade() {
    }

    // ✅ GETTERS (VERY IMPORTANT)
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Stock getStock() {
        return stock;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    // ✅ SETTERS
    public void setUser(User user) {
        this.user = user;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
