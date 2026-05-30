package com.example.cadastro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer etapa;

    @Column(nullable = false)
    private nomeEtapa tipo;

    @Column(nullable = false)
    private Boolean concluida;

    public Cronograma() {
    }

    public Cronograma(Long id, Integer etapa,nomeEtapa tipo, Boolean concluida) {
        this.id = id;
        this.etapa = etapa;
        this.tipo = tipo;
        this.concluida = concluida;
    }

    public Long getId() {
        return id;
    }

    public Integer getEtapa() {
        return etapa;
    }

    public nomeEtapa getNomeEtapas() {
        return tipo;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public void setNomeEtapa(nomeEtapa nomeEtapa) {
        this.tipo = nomeEtapa;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public nomeEtapa getNomeEtapa() {
        return tipo;
    }

    public void setTipo(nomeEtapa tipo) {
        this.tipo = tipo;
    }
}