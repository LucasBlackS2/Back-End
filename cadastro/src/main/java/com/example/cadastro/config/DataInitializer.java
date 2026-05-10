package com.example.cadastro.config;

import com.example.cadastro.entity.Cadastro;
import com.example.cadastro.entity.Login;
import com.example.cadastro.repository.CadastroRepository;
import com.example.cadastro.repository.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final String LUCAS_EMAIL = "lucas@hotmail.com";
    private static final String LUCAS_PASSWORD = "1234";
    private static final String LUIZ_EMAIL ="luiz@hotmail.com";
    private static final String LUIZ_PASSWORD = "LUIZ";


    private final CadastroRepository cadastroRepository;
    private final LoginRepository loginRepository;

    public DataInitializer(CadastroRepository cadastroRepository, LoginRepository loginRepository) {
        this.cadastroRepository = cadastroRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public void run(String... args) {
        cadastroRepository.findByEmail(LUCAS_EMAIL)
                .orElseGet(() -> cadastroRepository.save(new Cadastro(null, "lucas", LUCAS_EMAIL, LUCAS_PASSWORD)));
        cadastroRepository.findByEmail(LUIZ_EMAIL)
                .orElseGet(() -> cadastroRepository.save(new Cadastro(null, "luiz", LUIZ_EMAIL, LUIZ_PASSWORD)));


        loginRepository.findByEmail(LUCAS_EMAIL)
                .orElseGet(() -> loginRepository.save(new Login(null, LUCAS_EMAIL, LUCAS_PASSWORD)));
        loginRepository.findByEmail(LUIZ_EMAIL)
                .orElseGet(() -> loginRepository.save(new Login(null, LUIZ_EMAIL, LUIZ_PASSWORD)));

    }
}
