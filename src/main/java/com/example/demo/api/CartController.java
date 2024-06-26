package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

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

import com.example.demo.bean.ShoppingCart;
import com.example.demo.dao.ShoppingCartRepository;


@RestController
@Order(4)
@RequestMapping("/shopping-carts")
public class CartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getShoppingCarts(Model model) {
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) shoppingCartRepository.findAll();
        if (shoppingCarts == null || shoppingCarts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shoppingCarts);
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart newShoppingCart = shoppingCartRepository.save(shoppingCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(newShoppingCart);
    }

    @GetMapping("/{id}")
    public ShoppingCart getShoppingCartById(@PathVariable Long id) {
        Optional<ShoppingCart> result = shoppingCartRepository.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Carrinho de compras não encontrado com id: " + id);
        }
        return result.get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable Long id, @RequestBody ShoppingCart updatedShoppingCart) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findById(id);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCart shoppingCart = optionalShoppingCart.get();
            // Atualize os campos relevantes do carrinho de compras
            ShoppingCart updatedCart = shoppingCartRepository.save(shoppingCart);
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable Long id) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findById(id);
        if (optionalShoppingCart.isPresent()) {
            shoppingCartRepository.delete(optionalShoppingCart.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
