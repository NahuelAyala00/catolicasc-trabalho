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

import com.example.demo.bean.Usuario;
import com.example.demo.dao.UsuarioRepository;


@RestController
@Order(0)
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsuarioRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers(Model model) {
        List<Usuario> users = (List<Usuario>) userRepository.findAll();
        if (users == null || users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
    	Usuario newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{id}")
    public Usuario getUserById(@PathVariable Long id) {
        Optional<Usuario> result = userRepository.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
        return result.get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario updatedUser) {
        System.out.println("Received request to update user with ID: " + id);
        
        Optional<Usuario> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            System.out.println("User found: " + user);

            // Atualize os campos relevantes do usuário
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());

            Usuario savedUser = userRepository.save(user);
            System.out.println("User updated: " + savedUser);
            return ResponseEntity.ok(savedUser);
        } else {
            System.out.println("User not found with ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<Usuario> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}