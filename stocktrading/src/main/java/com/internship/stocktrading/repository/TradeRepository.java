package com.internship.stocktrading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.stocktrading.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findByUserId(Long userId);
}
