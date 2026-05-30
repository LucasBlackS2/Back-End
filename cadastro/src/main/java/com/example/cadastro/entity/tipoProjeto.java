package com.example.cadastro.entity;

public enum tipoProjeto {
    Casa("0 - Casa"),
    Edifício("1 - Edificio"),
    ProjetoDiferente("2 - Projeto Diferente");

    private final String descricao;

    tipoProjeto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

