package com.example.cadastro.controller;

import com.example.cadastro.entity.Funcionario;
import com.example.cadastro.repository.FuncionarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Operation(summary ="Criar Funcionário")
    @PostMapping("/cadastro")
    public Map<String, Object> cadastrar(@RequestBody Funcionario funcionario) {
        Funcionario salvo = funcionarioRepository.save(funcionario);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Cadastro realizado com sucesso!");
        response.put("funcionario", salvo);
        return response;
    }
@Operation(summary ="List de Funcionario")
    @GetMapping
    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }
}
