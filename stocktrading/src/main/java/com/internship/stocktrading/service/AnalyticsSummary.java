package com.internship.stocktrading.service;

public class AnalyticsSummary {

    private double availableBalance;
    private double totalInvested;
    private double currentValue;
    private double profitLoss;

    public AnalyticsSummary(double availableBalance,
                            double totalInvested,
                            double currentValue,
                            double profitLoss) {
        this.availableBalance = availableBalance;
        this.totalInvested = totalInvested;
        this.currentValue = currentValue;
        this.profitLoss = profitLoss;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTotalInvested() {
        return totalInvested;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public double getProfitLoss() {
        return profitLoss;
    }
}
