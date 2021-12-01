package com.nagarro.productcatalogsystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.productcatalogsystem.model.Product;
import com.nagarro.productcatalogsystem.repo.ProductRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            ProductRepository repository) {
        return args -> {
            Product shirt = new Product(
                    "shirt",
                    "good quality shirt",
                    "top wear",
                    "XL",
                    "red",
                    "raymond",
                    2000.00
            );
            Product jeans = new Product(
                    "jeans",
                    "good quality rugged jeans",
                    "bottom wear",
                    "M",
                    "black",
                    "H&M",
                    5999.99
            );
            Product cap = new Product(
                    "cap",
                    "nice fitting cap",
                    "top wear",
                    "L",
                    "white",
                    "raymond",
                    499.99
            );
            repository.saveAll(
                    List.of(shirt, jeans, cap)
            );
        };
    }
}
