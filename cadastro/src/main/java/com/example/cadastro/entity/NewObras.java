package com.example.cadastro.entity;

import jakarta.persistence.*;

    @Entity
    @Table(name="obras")
    public class NewObras {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 200)
        private String nome;

        @Column(nullable = false, unique = true, length = 200)
        private int mestrosQ;

        @Column(nullable = false, length = 120)
        private int dias;

        public NewObras(){

        }

        public NewObras(Long id, String nome, int mestrosQ, int dias) {
            this.id = id;
            this.nome = nome;
            this.mestrosQ = mestrosQ;
            this.dias = dias;

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

        public int getMestrosQ() {
            return mestrosQ;
        }

        public void setMestrosQ(int mestrosQ) {
            this.mestrosQ = mestrosQ;
        }

        public int getDias() {
            return dias;
        }

        public void setDias(int dias) {
            this.dias = dias;
        }
    }


