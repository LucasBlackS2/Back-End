package com.example.cadastro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cronograma")
public class Cronograma {

    @Id
    private Long id; // id fixo, sem @GeneratedValue

    private Integer etapa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NomeEtapa nomeEtapa;

    @Column(nullable = false)
    private Boolean concluida;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getEtapa() { return etapa; }
    public void setEtapa(Integer etapa) { this.etapa = etapa; }

    public NomeEtapa getNomeEtapa() { return nomeEtapa; }
    public void setNomeEtapa(NomeEtapa nomeEtapa) { this.nomeEtapa = nomeEtapa; }

    public Boolean getConcluida() { return concluida; }
    public void setConcluida(Boolean concluida) { this.concluida = concluida; }
}
