package com.example.cadastro.controller;

import com.example.cadastro.entity.Cadastro;
import com.example.cadastro.entity.Login;
import com.example.cadastro.repository.CadastroRepository;
import com.example.cadastro.repository.LoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    private final CadastroRepository cadastroRepository;
    private final LoginRepository loginRepository;

    public CadastroController(CadastroRepository cadastroRepository, LoginRepository loginRepository) {
        this.cadastroRepository = cadastroRepository;
        this.loginRepository = loginRepository;
    }

    @GetMapping
    public List<Cadastro> listar() {
        return cadastroRepository. findAll();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Cadastro cadastro) {
        if (cadastroRepository.findByEmail(cadastro.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja cadastrado");
        }

        Cadastro cadastroSalvo = cadastroRepository.save(cadastro);
        loginRepository.save(new Login(null, cadastro.getEmail(), cadastro.getSenha()));

        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroSalvo);
    }
}
