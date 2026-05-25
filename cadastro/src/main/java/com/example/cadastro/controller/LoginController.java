package com.example.cadastro.controller;

import com.example.cadastro.dto.LoginRequest;
import com.example.cadastro.entity.Login;
import com.example.cadastro.repository.LoginRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    @Operation
    @GetMapping({"/api", "/login"})
    public List<Login> listar() {
        return loginRepository.findAll();
    }
    @Operation
    @PostMapping({"/api/loginUser", "/api/login", "/login"})
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank() ||
                request.getSenha() == null || request.getSenha().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "success", false,
                            "message", "Email e senha sao obrigatorios"
                    ));
        }

        return loginRepository.findByEmail(request.getEmail())
                .map(login -> {
                    if (login.getSenha().equals(request.getSenha())) {
                        return ResponseEntity.ok(Map.of(
                                "success", true,
                                "message", "Login realizado com sucesso!"
                        ));
                    }

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Map.of(
                                    "success", false,
                                    "message", "Senha incorreta"
                            ));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "success", false,
                                "message", "Usuario nao encontrado"
                        )));
    }

    @PutMapping({"/api/login/{id}", "/login/{id}"})
    public ResponseEntity<?> atualizarLogin(@PathVariable Long id, @RequestBody LoginRequest loginRequest) {
        return loginRepository.findById(id)
                .map(usuario -> {
                    if (loginRequest.getSenha() == null || loginRequest.getSenha().isBlank()) {
                        return ResponseEntity.badRequest()
                                .body(Map.of(
                                        "success", false,
                                        "message", "Senha invalida"
                                ));
                    }

                    usuario.setSenha(loginRequest.getSenha());
                    loginRepository.save(usuario);

                    return ResponseEntity.ok(Map.of(
                            "success", true,
                            "message", "Senha atualizada com sucesso!"
                    ));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "success", false,
                                "message", "Usuario nao encontrado"
                        )));
    }

    @DeleteMapping("/api/cadastro/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        return loginRepository.findById(id)
                .map(usuario -> {
                    loginRepository.delete(usuario);
                    return ResponseEntity.ok(Map.of("success", true, "message", "Usuario deletado com sucesso!"));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Usuario nao encontrado")));
    }
}
