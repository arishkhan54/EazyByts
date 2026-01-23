package com.internship.stocktrading.service;

import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.internship.stocktrading.model.Stock;
import com.internship.stocktrading.repository.StockRepository;

@Service
public class StockPriceService {

    private final StockRepository stockRepo;
    private final Random random = new Random();

    public StockPriceService(StockRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    @Scheduled(fixedRate = 5000)
    public void fluctuatePrices() {

        for (Stock stock : stockRepo.findAll()) {

            double changePercent = (random.nextDouble() * 2 - 1); // -1% to +1%
            double newPrice = stock.getPrice() * (1 + changePercent / 100);

            if (newPrice < 1) {
                newPrice = stock.getPrice(); // safety
            }

            stock.setPrice(Math.round(newPrice * 100.0) / 100.0);
            stockRepo.save(stock);
        }
    }
}
