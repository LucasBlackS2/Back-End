package com.example.cadastro.controller;

import com.example.cadastro.entity.Material;
import com.example.cadastro.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialService service;

    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @Operation(summary ="Cadastrar/Criar material")
    @PostMapping
    public Material cadastrar(@RequestBody Material material) {
        return service.salvar(material);
    }

    @Operation(summary ="List de material cadastrados")
    @GetMapping
    public List<Material> listar() {
        return service.listarTodos();
    }

    @Operation(summary ="Delata Material pelo ID")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
