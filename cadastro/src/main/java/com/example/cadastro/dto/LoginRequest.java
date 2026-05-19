package com.example.cadastro.dto;

import lombok.Getter;

public class LoginRequest {

    @Getter
    private static String email;
    private static String senha;

    public LoginRequest() {
    }

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public void setEmail(String email) {
        LoginRequest.email = email;
    }

    public static String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        LoginRequest.senha = senha;
    }
}
