package com.internship.stocktrading.service;

import org.springframework.stereotype.Service;

import com.internship.stocktrading.model.Portfolio;
import com.internship.stocktrading.model.User;
import com.internship.stocktrading.repository.PortfolioRepository;
import com.internship.stocktrading.repository.UserRepository;

@Service
public class AnalyticsService {

    private final PortfolioRepository portfolioRepo;
    private final UserRepository userRepo;

    public AnalyticsService(PortfolioRepository portfolioRepo,
                            UserRepository userRepo) {
        this.portfolioRepo = portfolioRepo;
        this.userRepo = userRepo;
    }

    public AnalyticsSummary getSummary(Long userId) {

        User user = userRepo.findById(userId).orElseThrow();

        double invested = 0;
        double currentValue = 0;

        for (Portfolio p : portfolioRepo.findByUser(user)) {
            invested += p.getQuantity() * p.getAvgPrice();
            currentValue += p.getQuantity() * p.getStock().getPrice();
        }

        double profitLoss = currentValue - invested;

        return new AnalyticsSummary(
                user.getBalance(),
                invested,
                currentValue,
                profitLoss
        );
    }
}
