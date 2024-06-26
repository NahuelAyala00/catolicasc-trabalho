package com.example.demo;


import com.example.demo.bean.Product;
import com.example.demo.service.ProductService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExemploSalvandoProduto {


	@Autowired
    private ProductService productService;

    @PostConstruct
    public void init() {
        criarSapatos();
    }

    public void criarSapatos() {
        // Exemplo de criação de múltiplos sapatos
        String[] nomes = {"sapato 1", "sapato 2", "sapato 3"};
        double[] precos = {100.00, 85.00, 95.00};
        String[] descricoes = {
            "Descrição do Nike 1...",
            "Descrição do Adidas 2...",
            "Descrição do Puma 3..."
        };
        int[] stocks = {10, 15, 12};
        String[] marcas = {"Nike", "Adidas", "Puma"};

        for (int i = 0; i < nomes.length; i++) {
            Product produto = new Product();
            produto.setName(nomes[i]);
            produto.setPrice(precos[i]);
            produto.setDescription(descricoes[i]);
            produto.setStock(stocks[i]);
            produto.setBrand(marcas[i]);  // Adicionando a marca

            System.out.println("Criando produto: " + produto);

            productService.saveProduct(produto);
        }
    }

}