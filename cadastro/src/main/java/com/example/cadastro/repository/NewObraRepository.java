package com.example.cadastro.repository;

import com.example.cadastro.entity.NewObras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewObraRepository extends JpaRepository<NewObras, Long> {
    Optional<NewObras> findByNome(String nome);
}
