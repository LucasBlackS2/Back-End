package com.example.cadastro.config;

import com.example.cadastro.entity.Cadastro;
import com.example.cadastro.entity.Funcionario;
import com.example.cadastro.entity.Login;
import com.example.cadastro.repository.CadastroRepository;
import com.example.cadastro.repository.FuncionarioRepository;
import com.example.cadastro.repository.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final FuncionarioRepository funcionarioRepository;
    private final CadastroRepository cadastroRepository;
    private final LoginRepository loginRepository;

    public DataInitializer(FuncionarioRepository funcionarioRepository, CadastroRepository cadastroRepository, LoginRepository loginRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cadastroRepository = cadastroRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public void run(String... args) {
        criarUsuario("lucas", "lucas@hotmail.com", "1234");
        criarUsuario("luiz", "luiz@hotmail.com", "1234");
        criarUsuario("Luan", "Luan@hotmail.com", "luan");
        criarUsuario("Jur", "Jurandi@hotmail.com", "Jur");
    }

    private void criarUsuario(String nome, String email, String senha) {
        cadastroRepository.findByEmail(email)
                .orElseGet(() -> cadastroRepository.save(new Cadastro(null, nome, email, senha)));

        loginRepository.findByEmail(email)
                .orElseGet(() -> loginRepository.save(new Login(null, nome, email, senha)));


    }
}

