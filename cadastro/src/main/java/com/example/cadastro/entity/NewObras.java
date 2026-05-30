package com.example.cadastro.entity;

import jakarta.persistence.*;

    @Entity
    @Table(name="Newobras")
    public class NewObras {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 200)
        private String nome;

        @Column(nullable = false, unique = true, length = 200)
        private int metragem;

        @Column(nullable = false, length = 120)
        private int dias;

        @Column(nullable = false, length = 50)
        private tipoProjeto tipo ;

        public NewObras(){

        }

        public NewObras(Long id, String nome, int metragem, int dias, tipoProjeto tipo) {
            this.id = id;
            this.nome = nome;
            this.metragem = metragem;
            this.dias = dias;
            this.tipo = tipo;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getMetragem() {
            return metragem;
        }

        public void setMetragem(int metragem) {
            this.metragem = metragem;
        }

        public int getDias() {
            return dias;
        }

        public void setDias(int dias) {
            this.dias = dias;
        }

        public tipoProjeto getTipoProjeto() {
            return tipo;
        }
        public void setTipoProjeto( tipoProjeto  tipoProjeto) {
            this.tipo = tipoProjeto;
        }
    }


