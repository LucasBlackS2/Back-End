package com.example.cadastro.controller;

import com.example.cadastro.dto.LoginRequest;
import com.example.cadastro.dto.LoginResponse;
import com.example.cadastro.entity.Login;
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
@RequestMapping("/login")
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @GetMapping
    public List<Login> listar() {
        return loginRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<LoginResponse> autenticar(@RequestBody LoginRequest request) {
        boolean autenticado = loginRepository.findByEmailAndSenha(request.getEmail(), request.getSenha()).isPresent();

        if (!autenticado) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, "Email ou senha invalidos"));
        }

        return ResponseEntity.ok(new LoginResponse(true, "Login realizado com sucesso"));
    }
}
