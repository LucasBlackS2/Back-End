package com.example.cadastro.controller;

import com.example.cadastro.dto.LoginRequest;
import com.example.cadastro.entity.Cadastro;
import com.example.cadastro.entity.Login;
import com.example.cadastro.repository.CadastroRepository;
import com.example.cadastro.repository.LoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // Listar todos os cadastros
    @GetMapping
    public List<Cadastro> listar() {
        return cadastroRepository.findAll();
    }

    // Cadastrar novo usuário
    @PostMapping("/cadastroUser")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody LoginRequest loginRequest) {
        Login novo = new Login();
        novo.setEmail(loginRequest.getEmail());
        novo.setSenha(loginRequest.getSenha());
        loginRepository.save(novo);
        return ResponseEntity.ok(Map.of("success", true, "message", "Usuário cadastrado com sucesso!"));
    }

    // Atualizar senha
    @PutMapping("/{id}/senha")
    public ResponseEntity<?> atualizarSenha(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return cadastroRepository.findById(id)
                .map(cadastro -> {
                    String novaSenha = body.get("senha");
                    if (novaSenha == null || novaSenha.isBlank()) {
                        return ResponseEntity.badRequest()
                                .body(Map.of("success", false, "message", "Senha inválida"));
                    }
                    cadastro.setSenha(novaSenha);
                    cadastroRepository.save(cadastro);
                    return ResponseEntity.ok(Map.of("success", true, "message", "Senha atualizada com sucesso!"));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Usuário não encontrado")));
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        return cadastroRepository.findById(id)
                .map(cadastro -> {
                    cadastroRepository.delete(cadastro);
                    return ResponseEntity.ok(Map.of("success", true, "message", "Usuário deletado com sucesso!"));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Usuário não encontrado")));
    }
}
