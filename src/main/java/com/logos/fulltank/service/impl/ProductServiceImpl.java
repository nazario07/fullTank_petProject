package com.logos.fulltank.service.impl;

import com.logos.fulltank.dao.ProductDao;
import com.logos.fulltank.entity.Product;
import com.logos.fulltank.exception.ProductNotFoundException;
import com.logos.fulltank.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Product Service")
public class ProductServiceImpl  implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product createProduct(Product product) {
        return productDao.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public Product getById(int id) throws ProductNotFoundException {
        Optional<Product> byId = productDao.findById(id);
        if (byId.isPresent()) {
            log.info("Information about product with id " + id + " ready for you");
            return byId.get();
        } else {
            log.error("Product with id " + id + " is not exist");
            throw new ProductNotFoundException("Product with id " + id + " is not exist");
        }
    }

    @Override
    public void deleteProduct(int id) throws ProductNotFoundException {
        Optional<Product> byId = productDao.findById(id);
        if (byId.isPresent()) {
            productDao.deleteById(id);
            log.info("Product with id " + id + " was deleted");
        } else {
            log.error("Product with id " + id + " is not exist");
            throw new ProductNotFoundException("Product with id " + id + " is not exist");
        }
    }
}
