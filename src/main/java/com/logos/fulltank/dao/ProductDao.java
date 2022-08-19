package com.logos.fulltank.dao;

import com.logos.fulltank.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
