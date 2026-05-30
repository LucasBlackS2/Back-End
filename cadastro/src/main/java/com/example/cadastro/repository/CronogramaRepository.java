package com.example.cadastro.repository;

import com.example.cadastro.entity.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {
    Optional<Cronograma> findByEtapa(Integer etapa);
}
