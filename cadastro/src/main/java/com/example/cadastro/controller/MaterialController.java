package com.example.cadastro.controller;

import com.example.cadastro.entity.Material;
import com.example.cadastro.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialService service;

    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @PostMapping
    public Material cadastrar(@RequestBody Material material) {
        return service.salvar(material);
    }

    @GetMapping
    public List<Material> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
