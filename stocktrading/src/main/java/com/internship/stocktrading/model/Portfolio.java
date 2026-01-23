package com.internship.stocktrading.model;

import jakarta.persistence.*;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Stock stock;

    private int quantity;
    private double avgPrice;

    public Portfolio() {}

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Stock getStock() { return stock; }
    public int getQuantity() { return quantity; }
    public double getAvgPrice() { return avgPrice; }

    public void setUser(User user) { this.user = user; }
    public void setStock(Stock stock) { this.stock = stock; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setAvgPrice(double avgPrice) { this.avgPrice = avgPrice; }
}
