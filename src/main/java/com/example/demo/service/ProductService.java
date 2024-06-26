package com.example.demo.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Product;
import com.example.demo.dao.ProductRepository;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    

    public List<Product> getAllProducts() {
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        iterable.forEach(products::add);
        return products;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    
}

