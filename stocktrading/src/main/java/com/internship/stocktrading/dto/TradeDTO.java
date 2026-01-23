package com.internship.stocktrading.dto;

import java.time.LocalDateTime;

public class TradeDTO {

    private String stockSymbol;
    private String type;
    private int quantity;
    private double price;
    private LocalDateTime time;

    public TradeDTO(String stockSymbol,
                    String type,
                    int quantity,
                    double price,
                    LocalDateTime time) {
        this.stockSymbol = stockSymbol;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.time = time;
    }

    public String getStockSymbol() {
        return stockSymbol;
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
}
