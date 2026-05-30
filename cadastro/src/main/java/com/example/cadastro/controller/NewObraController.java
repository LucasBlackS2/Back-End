package com.example.cadastro.controller;

import com.example.cadastro.entity.NewObras;
import com.example.cadastro.repository.NewObraRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/NewObras")
public class NewObraController {

    @Autowired
    private NewObraRepository newObrasRepository;

 @Operation(summary ="Lista de novas obras")
    @GetMapping
    public ResponseEntity<List<NewObras>> listarTodas() {
        return ResponseEntity.ok(newObrasRepository.findAll()); // 200
    }

@Operation(summary ="Cadastrados de Novas Obras ")
    @PostMapping
    public ResponseEntity<NewObras> cadastrar(@RequestBody NewObras obra) {
        NewObras novaObra = newObrasRepository.save(obra);

        return ResponseEntity
                .created(URI.create("/obras/" + novaObra.getId())) // 201
                .body(novaObra);
    }
@Operation(summary ="atualizer as obras ")
    @PutMapping("/{id}")
    public ResponseEntity<NewObras> atualizar(
            @PathVariable Long id,
            @RequestBody NewObras obraAtualizada) {

        return newObrasRepository.findById(id)
                .map(obra -> {
                    obra.setNome(obraAtualizada.getNome());
                    obra.setMetragem(obraAtualizada.getMetragem());
                    obra.setDias(obraAtualizada.getDias());

                    return ResponseEntity.ok(newObrasRepository.save(obra)); // 200
                })
                .orElse(ResponseEntity.notFound().build()); // 404
    }
@Operation(summary ="deletar obras ja finalizada ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        if (!newObrasRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }

        newObrasRepository.deleteById(id);

        return ResponseEntity.ok().build(); // 200
    }
}
