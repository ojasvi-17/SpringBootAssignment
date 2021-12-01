package com.nagarro.productcatalogsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nagarro.productcatalogsystem.model.Product;
import com.nagarro.productcatalogsystem.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteStudent(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(@PathVariable("productId") Long productId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) Double price)
 {
        productService.updateProduct(productId, name, description, price);
    }
}
