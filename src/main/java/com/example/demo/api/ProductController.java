package com.example.demo.api;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ExemploSalvandoProduto;
import com.example.demo.bean.Product;
import com.example.demo.dao.ProductRepository;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;



@RestController
@Order(3)
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductRepository produtoRepository;



 
    
    @GetMapping
    public ResponseEntity<List<Product>> getProdutos(Model model) {
        List<Product> produtos = (List<Product>) produtoRepository.findAll();
        
        if (produtos == null || produtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Product> createProduto(@RequestBody Product produto) {
    	Product novoProduto = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping("/{id}")
    public Product getProdutoById(@PathVariable Long id) {
        Optional<Product> result = produtoRepository.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        return result.get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduto(@PathVariable Long id, @RequestBody Product produtoAtualizado) {
        Optional<Product> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
        	Product produto = optionalProduto.get();
            produto.setName(produtoAtualizado.getName());
            produto.setDescription(produtoAtualizado.getDescription());
            produto.setPrice(produtoAtualizado.getPrice());
            Product updatedProduto = produtoRepository.save(produto);
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        Optional<Product> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            produtoRepository.delete(optionalProduto.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllProdutos() {
        produtoRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
    
    
    
    
    
    
    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getProdutosPorFaixaDePreco(@RequestParam(value = "min", required = false) Double minPrice,
                                                                    @RequestParam(value = "max", required = false) Double maxPrice) {
        List<Product> produtos;

        // Verifica se ambos os parâmetros de preço estão presentes
        if (minPrice != null && maxPrice != null) {
            produtos = produtoRepository.findByPriceBetween(minPrice, maxPrice);
        }
        // Caso apenas o mínimo seja especificado
        else if (minPrice != null) {
            produtos = produtoRepository.findByPriceGreaterThanEqual(minPrice);
        }
        // Caso apenas o máximo seja especificado
        else if (maxPrice != null) {
            produtos = produtoRepository.findByPriceLessThanEqual(maxPrice);
        }
        // Caso nenhum parâmetro seja especificado, retorna todos os produtos
        else {
            produtos = (List<Product>) produtoRepository.findAll();
        }

        // Verifica se a lista de produtos está vazia
        if (produtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtos);
    }

    
    
    
   


        @GetMapping("/api/products/puma")
        public List<Product> getPumaProducts() {
            return produtoRepository.findByBrand("Puma");
        }

        @GetMapping("/api/products/nike")
        public List<Product> getNikeProducts() {
            return produtoRepository.findByBrand("Nike");
        }

        @GetMapping("/api/products/adidas")
        public List<Product> getAdidasProducts() {
            return produtoRepository.findByBrand("Adidas");
        }
    }


