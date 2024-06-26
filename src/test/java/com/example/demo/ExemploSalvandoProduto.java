package com.example.demo;
import org.junit.jupiter.api.Test;
import com.example.demo.bean.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.service.ProductService;


@ExtendWith(SpringExtension.class) 
@SpringBootTest 
public class ExemploSalvandoProduto {

    @Autowired
    private ProductService productService;

    @Test 
    public void testSaveProduct() {

     /*instância de Product
        Product produto = new Product();

        produto.setName("Nome do Produto");
        produto.setPrice(100.00);
        produto.setDescription("Descrição do Produto");
        produto.setStock(10);

        System.out.println(produto);

        productService.saveProduct(produto);
*/
    }
}
