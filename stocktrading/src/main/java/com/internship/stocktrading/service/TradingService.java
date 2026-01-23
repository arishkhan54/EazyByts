package com.internship.stocktrading.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.internship.stocktrading.dto.TradeDTO;
import com.internship.stocktrading.model.Portfolio;
import com.internship.stocktrading.model.Stock;
import com.internship.stocktrading.model.Trade;
import com.internship.stocktrading.model.User;
import com.internship.stocktrading.repository.PortfolioRepository;
import com.internship.stocktrading.repository.StockRepository;
import com.internship.stocktrading.repository.TradeRepository;
import com.internship.stocktrading.repository.UserRepository;

@Service
public class TradingService {

    private final UserRepository userRepo;
    private final StockRepository stockRepo;
    private final TradeRepository tradeRepo;
    private final PortfolioRepository portfolioRepo;
    private final PortfolioService portfolioService;

    public TradingService(UserRepository userRepo,
                          StockRepository stockRepo,
                          TradeRepository tradeRepo,
                          PortfolioRepository portfolioRepo,
                          PortfolioService portfolioService) {
        this.userRepo = userRepo;
        this.stockRepo = stockRepo;
        this.tradeRepo = tradeRepo;
        this.portfolioRepo = portfolioRepo;
        this.portfolioService = portfolioService;
    }

    /* ===================== BUY ===================== */
    public void buy(Long userId, Long stockId, int qty) {

        User user = userRepo.findById(userId).orElseThrow();
        Stock stock = stockRepo.findById(stockId).orElseThrow();

        double cost = stock.getPrice() * qty;
        if (user.getBalance() < cost) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setBalance(user.getBalance() - cost);
        userRepo.save(user);

        Trade trade = new Trade();
        trade.setUser(user);
        trade.setStock(stock);
        trade.setType("BUY");
        trade.setQuantity(qty);
        trade.setPrice(stock.getPrice());
        trade.setTime(LocalDateTime.now());
        tradeRepo.save(trade);

        portfolioService.updatePortfolio(userId, stockId, qty, stock.getPrice());
    }

    /* ===================== SELL ===================== */
    public void sell(Long userId, Long stockId, int qty) {

        User user = userRepo.findById(userId).orElseThrow();
        Stock stock = stockRepo.findById(stockId).orElseThrow();

        Portfolio portfolio = portfolioRepo
                .findByUserAndStock(user, stock)
                .orElseThrow(() -> new RuntimeException("No holdings found"));

        if (portfolio.getQuantity() < qty) {
            throw new RuntimeException("Not enough quantity to sell");
        }

        double sellAmount = stock.getPrice() * qty;

        user.setBalance(user.getBalance() + sellAmount);
        userRepo.save(user);

        portfolio.setQuantity(portfolio.getQuantity() - qty);
        if (portfolio.getQuantity() == 0) {
            portfolioRepo.delete(portfolio);
        } else {
            portfolioRepo.save(portfolio);
        }

        Trade trade = new Trade();
        trade.setUser(user);
        trade.setStock(stock);
        trade.setType("SELL");
        trade.setQuantity(qty);
        trade.setPrice(stock.getPrice());
        trade.setTime(LocalDateTime.now());
        tradeRepo.save(trade);
    }

    /* ===================== HISTORY ===================== */
    public List<TradeDTO> getTradeHistory(Long userId) {

        return tradeRepo.findByUserId(userId)
                .stream()
                .map(t -> new TradeDTO(
                        t.getStock().getSymbol(),
                        t.getType(),
                        t.getQuantity(),
                        t.getPrice(),
                        t.getTime()
                ))
                .collect(Collectors.toList());
    }
}
