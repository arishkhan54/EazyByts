package com.internship.stocktrading.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.internship.stocktrading.model.Stock;
import com.internship.stocktrading.repository.StockRepository;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class StockController {

    private final StockRepository stockRepo;

    public StockController(StockRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockRepo.findAll();
    }
}
