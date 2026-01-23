package com.internship.stocktrading.dto;

public class PortfolioDTO {

    private String stock;
    private int quantity;
    private double avgPrice;
    private double currentPrice;

    public PortfolioDTO(String stock, int quantity,
                        double avgPrice, double currentPrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.avgPrice = avgPrice;
        this.currentPrice = currentPrice;
    }

    public String getStock() { return stock; }
    public int getQuantity() { return quantity; }
    public double getAvgPrice() { return avgPrice; }
    public double getCurrentPrice() { return currentPrice; }
}
