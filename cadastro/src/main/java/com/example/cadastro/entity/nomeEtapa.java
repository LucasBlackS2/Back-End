package com.example.cadastro.entity;

public enum nomeEtapa {

    Planejamento("1 - Planejamento Inicial"),
    PreparaçaoDoTerreno("2 - Preparação do Terreno"),
    ProjetoDiferente("3 - Fundação"),
    Estrutura("Estrutura"),
    Alvenaria("Alvenaria"),
    Instalaçoes("Instalações"),
    Cobertura("Cobertura"),
    Acabamento("Acabamentos"),
    Finalizaçao("Finalização");

    private final String descricao;

    nomeEtapa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}



