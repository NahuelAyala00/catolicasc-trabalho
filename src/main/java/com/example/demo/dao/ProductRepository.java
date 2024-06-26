package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long>{

	List<Product> findByBrand(String brand);
	
	 	@Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
	    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

	    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice")
	    List<Product> findByPriceGreaterThanEqual(Double minPrice);
	    
	    @Query("SELECT p FROM Product p WHERE p.price <= :maxPrice")
	    List<Product> findByPriceLessThanEqual(Double maxPrice);
}
