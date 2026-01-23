package com.internship.stocktrading.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.internship.stocktrading.dto.PortfolioDTO;
import com.internship.stocktrading.service.PortfolioService;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin
public class PortfolioController {

    private final PortfolioService service;

    public PortfolioController(PortfolioService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public List<PortfolioDTO> portfolio(@PathVariable Long userId) {
        return service.getPortfolioDTO(userId);
    }
}
