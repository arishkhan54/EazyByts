package com.internship.stocktrading.model;

import jakarta.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private double price;

    private String market; 

    public Stock() {}

    public Long getId() { return id; }
    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }
    public String getMarket() { return market; }

    public void setSymbol(String symbol) { this.symbol = symbol; }
    public void setPrice(double price) { this.price = price; }
    public void setMarket(String market) { this.market = market; }
}
