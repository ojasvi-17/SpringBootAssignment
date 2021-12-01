package com.nagarro.productcatalogsystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.nagarro.productcatalogsystem.model.Product;
import com.nagarro.productcatalogsystem.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductByName(product.getName());
        if (productOptional.isPresent()) {
            throw new IllegalStateException("Product already present");
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException(
                    "product with id "+ productId +" does not exists");
        }
        productRepository.deleteById(productId);
    }

    public List<Object[]> findProductsByBrand() {
        return productRepository.findProductsByBrand();

    }

	public void updateProduct(Long productId, String name, String description, Double price) {
		Product product = productRepository.findById(productId)
		        .orElseThrow(() -> new IllegalStateException(
		                "student with id "+ productId +" does not exists"));

		if(name != null &&
		        name.length() > 0) {
		    product.setName(name);
		}
		
		if(description != null && description.length() >0) {
		    product.setDescription(description);
		}
		
		if(price != 0 ) {
		    product.setPrice(price);
		}

		
	}
}
