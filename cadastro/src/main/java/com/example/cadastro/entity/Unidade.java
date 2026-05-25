package com.example.cadastro.entity;

public enum Unidade {
    UNID("unid"),
    M2("m2"),
    KG("kg");

    private final String descricao;

    Unidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
