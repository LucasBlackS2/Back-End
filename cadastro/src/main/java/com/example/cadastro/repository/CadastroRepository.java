package com.example.cadastro.repository;

import com.example.cadastro.entity.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CadastroRepository
        extends JpaRepository<Cadastro, Long> {

    Optional<Cadastro> findByEmail(String email);
}