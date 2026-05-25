package com.example.cadastro.entity;

public enum Fase {
    BASE("1 - Base"),
    ESTRUTURA("2 - Estrutura"),
    ACABAMENTO("3 - Acabamento");

    private final String descricao;

    Fase(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
