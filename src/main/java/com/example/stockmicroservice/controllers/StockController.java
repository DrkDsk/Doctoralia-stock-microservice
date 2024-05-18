package com.example.stockmicroservice.controllers;

import com.example.stockmicroservice.entities.Stock;
import com.example.stockmicroservice.repositories.StockRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockRepository stockRepository;

    StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @RequestMapping("/{code}")
    public Boolean available(@PathVariable String code) {
        Optional<Stock> stock = stockRepository.findByCode(code);
        stock.orElseThrow(() -> new RuntimeException("Product not found with code " + code));

        return stock.get().getQuantity() > 0;
    }

}
