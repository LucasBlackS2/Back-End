package com.example.cadastro.service;

import com.example.cadastro.entity.Material;
import com.example.cadastro.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository repository;

    public MaterialService(MaterialRepository repository) {
        this.repository = repository;
    }

    public Material salvar(Material material) {
        return repository.save(material);
    }

    public List<Material> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
