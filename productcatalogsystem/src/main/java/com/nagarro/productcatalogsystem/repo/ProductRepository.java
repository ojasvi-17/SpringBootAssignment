package com.nagarro.productcatalogsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.productcatalogsystem.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name = ?1")
    Optional<Product> findProductByName(String name);

    @Query("SELECT p.brand, count(p.brand) FROM Product p GROUP BY brand")
    List<Object[]> findProductsByBrand();
}
