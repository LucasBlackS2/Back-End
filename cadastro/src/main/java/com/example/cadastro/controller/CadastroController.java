package com.example.cadastro.controller;

import com.example.cadastro.dto.LoginRequest;
import com.example.cadastro.entity.Cadastro;
import com.example.cadastro.entity.Login;
import com.example.cadastro.repository.CadastroRepository;
import com.example.cadastro.repository.LoginRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/cadastro")
@RestController
public class CadastroController {

    private final CadastroRepository cadastroRepository;
    private final LoginRepository loginRepository;

    public CadastroController(CadastroRepository cadastroRepository, LoginRepository loginRepository) {
        this.cadastroRepository = cadastroRepository;
        this.loginRepository = loginRepository;
    }
@Operation(summary ="lista de Cliente")
    @GetMapping("/cadastro")
    public List<Cadastro> listar() {
        return cadastroRepository.findAll();
    }
@Operation(summary ="Criar Cliente")
    @PostMapping({"/cadastro", "/cadastro/cadastroUser"})
    public ResponseEntity<?> cadastrarUsuario(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getNome() == null || loginRequest.getNome().isBlank() ||
                loginRequest.getEmail() == null || loginRequest.getEmail().isBlank() ||
                loginRequest.getSenha() == null || loginRequest.getSenha().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "success", false,
                            "message", "Nome, email e senha sao obrigatorios"
                    ));
        }

        if (loginRepository.findByEmail(loginRequest.getEmail()).isPresent() ||
                cadastroRepository.findByEmail(loginRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "success", false,
                            "message", "Email ja cadastrado"
                    ));
        }

        Cadastro cadastro = new Cadastro();
        cadastro.setNome(loginRequest.getNome());
        cadastro.setEmail(loginRequest.getEmail());
        cadastro.setSenha(loginRequest.getSenha());
        cadastroRepository.save(cadastro);

        Login novo = new Login();
        novo.setNome(loginRequest.getNome());
        novo.setEmail(loginRequest.getEmail());
        novo.setSenha(loginRequest.getSenha());
        loginRepository.save(novo);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Usuario cadastrado com sucesso!"
        ));
    }
@Operation(summary ="atualizar senha do Cliente")
    @PutMapping("/cadastro/{id}/senha")
    public ResponseEntity<?> atualizarSenha(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return cadastroRepository.findById(id)
                .map(cadastro -> {
                    String novaSenha = body.get("senha");
                    if (novaSenha == null || novaSenha.isBlank()) {
                        return ResponseEntity.badRequest()
                                .body(Map.of("success", false, "message", "Senha invalida"));
                    }
                    cadastro.setSenha(novaSenha);
                    cadastroRepository.save(cadastro);
                    return ResponseEntity.ok(Map.of("success", true, "message", "Senha atualizada com sucesso!"));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Usuario nao encontrado")));
    }
@Operation(summary ="Delata Cliente pelo ID")
    @DeleteMapping("/cadastro/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        return cadastroRepository.findById(id)
                .map(cadastro -> {
                    cadastroRepository.delete(cadastro);
                    return ResponseEntity.ok(Map.of("success", true, "message", "Usuario deletado com sucesso!"));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Usuario nao encontrado")));
    }
}