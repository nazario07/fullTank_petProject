package com.logos.fulltank.service;

import com.logos.fulltank.dao.ProductDao;
import com.logos.fulltank.entity.FuelName;
import com.logos.fulltank.entity.Product;
import com.logos.fulltank.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductDao productDao;

    @Test
    void createProduct() {
        Product product = new Product(FuelName.A92,55);
        Mockito.when(productDao.save(product)).thenReturn(product);
        Product newProduct = productService.createProduct(product);
        Assertions.assertEquals(newProduct, product);
    }

    @Test
    void getAll() {
        Product product = new Product(FuelName.A92,55);
        Product product1 = new Product(FuelName.A95,57);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);

        Mockito.when(productDao.findAll()).thenReturn(products);
        List<Product> all = productService.getAll();
        Assertions.assertEquals(all,products);

    }

    @Test
    void getById() throws ProductNotFoundException {
        Product product = new Product(FuelName.A92,55);
        Mockito.when(productDao.findById(product.getId())).thenReturn(Optional.of(product));
        Product byId = productService.getById(product.getId());

        Assertions.assertEquals(product,Optional.of(byId).get());


    }

    @Test
    void deleteProduct() {
    }
}