package com.example.cadastro.controller;

import com.example.cadastro.entity.Cronograma;
import com.example.cadastro.repository.CronogramaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cronograma")
@CrossOrigin("*")
public class CronogramaController {

    @Autowired
    private CronogramaRepository repository;

    @Operation(summary = "Listar etapas")
    @GetMapping
    public ResponseEntity<List<Cronograma>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Operation(summary = "Cadastrar etapa")
    @PostMapping
    public ResponseEntity<Cronograma> cadastrar(
            @RequestBody Cronograma cronograma) {

        Cronograma novo = repository.save(cronograma);

        return ResponseEntity
                .created(URI.create("/cronograma/" + novo.getId()))
                .body(novo);
    }

    @Operation(summary = "Atualizar etapa")
    @PutMapping("/{id}")
    public ResponseEntity<Cronograma> atualizar(
            @PathVariable Long id,
            @RequestBody Cronograma cronogramaAtualizado) {

        return repository.findById(id)
                .map(cronograma -> {
                    cronograma.setEtapa(cronogramaAtualizado.getEtapa());
                    cronograma.setNomeEtapa(cronogramaAtualizado.getNomeEtapa());
                    cronograma.setConcluida(cronogramaAtualizado.getConcluida());
                    return ResponseEntity.ok(repository.save(cronograma));
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @Operation(summary = "Excluir etapa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
