package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;


import org.springframework.stereotype.Repository;

import com.example.demo.bean.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}

