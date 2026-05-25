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
@RequestMapping("/obras")
@CrossOrigin("*")
public class NewObraController {

    @Autowired
    private NewObraRepository newObrasRepository;
 @Operation
    @GetMapping
    public ResponseEntity<List<NewObras>> listarTodas() {
        return ResponseEntity.ok(newObrasRepository.findAll()); // 200
    }
@Operation
    @PostMapping
    public ResponseEntity<NewObras> cadastrar(@RequestBody NewObras obra) {
        NewObras novaObra = newObrasRepository.save(obra);

        return ResponseEntity
                .created(URI.create("/obras/" + novaObra.getId())) // 201
                .body(novaObra);
    }
@Operation
    @PutMapping("/{id}")
    public ResponseEntity<NewObras> atualizar(
            @PathVariable Long id,
            @RequestBody NewObras obraAtualizada) {

        return newObrasRepository.findById(id)
                .map(obra -> {
                    obra.setNome(obraAtualizada.getNome());
                    obra.setMestrosQ(obraAtualizada.getMestrosQ());
                    obra.setDias(obraAtualizada.getDias());

                    return ResponseEntity.ok(newObrasRepository.save(obra)); // 200
                })
                .orElse(ResponseEntity.notFound().build()); // 404
    }
@Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        if (!newObrasRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }

        newObrasRepository.deleteById(id);

        return ResponseEntity.ok().build(); // 200
    }
}
