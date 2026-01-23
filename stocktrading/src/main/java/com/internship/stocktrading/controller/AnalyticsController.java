package com.internship.stocktrading.controller;

import org.springframework.web.bind.annotation.*;

import com.internship.stocktrading.service.AnalyticsService;
import com.internship.stocktrading.service.AnalyticsSummary;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class AnalyticsController {

    private final AnalyticsService service;

    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public AnalyticsSummary getAnalytics(@PathVariable Long userId) {
        return service.getSummary(userId);
    }
}
