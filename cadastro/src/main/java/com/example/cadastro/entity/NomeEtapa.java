package com.example.cadastro.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NomeEtapa {
    PLANEJAMENTO("Planejamento Inicial"),
    PREPARACAO_TERRENO("Preparação do Terreno"),
    FUNDACAO("Fundação"),
    ESTRUTURA("Estrutura"),
    ALVENARIA("Alvenaria"),
    INSTALACOES("Instalações"),
    COBERTURA("Cobertura"),
    ACABAMENTOS("Acabamentos"),
    FINALIZACAO("Finalização");

    private final String descricao;

    NomeEtapa(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static NomeEtapa fromValue(String value) {
        for (NomeEtapa etapa : values()) {
            if (etapa.descricao.equalsIgnoreCase(value)) {
                return etapa;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + value);
    }
}
