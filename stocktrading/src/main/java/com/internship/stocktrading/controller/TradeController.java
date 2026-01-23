package com.internship.stocktrading.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.internship.stocktrading.dto.TradeDTO;
import com.internship.stocktrading.service.TradingService;

@RestController
@RequestMapping("/api/trade")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class TradeController {

    private final TradingService service;

    public TradeController(TradingService service) {
        this.service = service;
    }

    @PostMapping("/buy")
    public String buy(@RequestParam Long userId,
                      @RequestParam Long stockId,
                      @RequestParam int qty) {

        service.buy(userId, stockId, qty);
        return "BUY SUCCESS";
    }

    @PostMapping("/sell")
    public String sell(@RequestParam Long userId,
                       @RequestParam Long stockId,
                       @RequestParam int qty) {

        service.sell(userId, stockId, qty);
        return "SELL SUCCESS";
    }

    @GetMapping("/history/{userId}")
    public List<TradeDTO> history(@PathVariable Long userId) {
        return service.getTradeHistory(userId);
    }
}
