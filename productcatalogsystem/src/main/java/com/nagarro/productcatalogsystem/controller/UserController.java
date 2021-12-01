package com.nagarro.productcatalogsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productcatalogsystem.model.Product;
import com.nagarro.productcatalogsystem.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final ProductService productService;

    @Autowired
    public UserController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProductsForUser() {
        return productService.getProducts();
    }
}

