package com.internship.stocktrading.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.internship.stocktrading.dto.PortfolioDTO;
import com.internship.stocktrading.model.*;
import com.internship.stocktrading.repository.*;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepo;
    private final UserRepository userRepo;
    private final StockRepository stockRepo;

    public PortfolioService(PortfolioRepository portfolioRepo,
                            UserRepository userRepo,
                            StockRepository stockRepo) {
        this.portfolioRepo = portfolioRepo;
        this.userRepo = userRepo;
        this.stockRepo = stockRepo;
    }

    public void updatePortfolio(Long userId, Long stockId, int qty, double price) {

        User user = userRepo.findById(userId).orElseThrow();
        Stock stock = stockRepo.findById(stockId).orElseThrow();

        Portfolio p = portfolioRepo.findByUserAndStock(user, stock)
                .orElse(new Portfolio());

        if (p.getId() == null) {
            p.setUser(user);
            p.setStock(stock);
            p.setQuantity(qty);
            p.setAvgPrice(price);
        } else {
            int totalQty = p.getQuantity() + qty;
            double avg =
                    ((p.getAvgPrice() * p.getQuantity()) + (price * qty))
                            / totalQty;

            p.setQuantity(totalQty);
            p.setAvgPrice(avg);
        }
        portfolioRepo.save(p);
    }

    public List<PortfolioDTO> getPortfolioDTO(Long userId) {

        User user = userRepo.findById(userId).orElseThrow();

        return portfolioRepo.findByUser(user).stream()
                .map(p -> new PortfolioDTO(
                        p.getStock().getSymbol(),
                        p.getQuantity(),
                        p.getAvgPrice(),
                        p.getStock().getPrice()))
                .collect(Collectors.toList());
    }
}
