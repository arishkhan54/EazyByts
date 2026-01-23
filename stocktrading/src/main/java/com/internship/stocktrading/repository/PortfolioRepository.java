package com.internship.stocktrading.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.stocktrading.model.Portfolio;
import com.internship.stocktrading.model.Stock;
import com.internship.stocktrading.model.User;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByUserAndStock(User user, Stock stock);

    List<Portfolio> findByUser(User user);
}
