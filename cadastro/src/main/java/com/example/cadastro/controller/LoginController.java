package com.example.cadastro.controller;

import com.example.cadastro.dto.LoginRequest;
import com.example.cadastro.dto.LoginResponse;
import com.example.cadastro.entity.Login;
import com.example.cadastro.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "**")
@RestController
@RequestMapping("/api")
public class  LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @GetMapping
    public List<Login> listar() {
        return loginRepository.findAll();
    }

    @Autowired
    private LoginRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String senha = body.get("senha");

        return loginRepository.findByEmail(email)
                .map(login -> {
                    if (login.getSenha().equals(senha)) {
                        return ResponseEntity.ok(Map.of("success", true, "message", "Login realizado com sucesso!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of("success", false, "message", "Senha incorreta"));
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Usuário não encontrado")));
    }



    @PutMapping("/login/{id}")
    public ResponseEntity<?> atualizarLogin(@PathVariable Long id, @RequestBody LoginRequest loginRequest) {
        return loginRepository.findById(id)
                .map(usuario -> {
                    if (LoginRequest.getSenha() == null || LoginRequest.getSenha().isBlank()) {
                        return ResponseEntity.badRequest()
                                .body(Map.of("success", false, "message", "Senha inválida"));
                    }
                    usuario.setSenha(LoginRequest.getSenha());
                    loginRepository.save(usuario);
                    return ResponseEntity.ok(Map.of("success", true, "message", "Senha atualizada com sucesso!"));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Usuário não encontrado")));
    }

    @DeleteMapping("/cadastro/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        return loginRepository.findById(id)
                .map(usuario -> {
                    loginRepository.delete(usuario);
                    return ResponseEntity.ok(Map.of("success", true, "message", "Usuário deletado com sucesso!"));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Usuário não encontrado")));
    }
}
