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
    private static final String LUIZ_PASSWORD = "1234";
    private static final String LUAN_EMAIL="Luan@hotmail.com";
    private static final String LUAN_PASSWORD = "gay";
    private static final String EMA_EMAIL = "Jurandi@hotmail.com";
    private static final String EMA_PASSWORD ="Jur";


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
        cadastroRepository.findByEmail(LUAN_EMAIL)
                .orElseGet(() -> cadastroRepository.save(new Cadastro(null, "Luan", LUAN_EMAIL, LUAN_PASSWORD)));
        cadastroRepository.findByEmail(EMA_EMAIL)
                .orElseGet(() -> cadastroRepository.save(new Cadastro(null, "Jur", EMA_EMAIL, EMA_PASSWORD)));
        loginRepository.findByEmail(LUCAS_EMAIL)
                .orElseGet(() -> loginRepository.save(new Login(null, LUCAS_EMAIL, LUCAS_PASSWORD)));
        loginRepository.findByEmail(LUIZ_EMAIL)
                .orElseGet(() -> loginRepository.save(new Login(null, LUIZ_EMAIL, LUIZ_PASSWORD)));
        loginRepository.findByEmail(LUAN_EMAIL)
                .orElseGet(() -> loginRepository.save(new Login(null, LUAN_EMAIL, LUAN_PASSWORD)));
        loginRepository.findByEmail(EMA_EMAIL)
                .orElseGet(() -> loginRepository.save(new Login(null, EMA_EMAIL, EMA_PASSWORD)));


    }
}
