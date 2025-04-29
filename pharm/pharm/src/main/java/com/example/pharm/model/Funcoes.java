package com.example.pharm.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "funcoes")
public class Funcoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Corrigido de @Colunm
    private String nome;

    @ManyToOne
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(
            name = "fk_status_funcoes",
            foreignKeyDefinition = "FOREIGN KEY (status_descricao) REFERENCES STATUS(descricao) ON DELETE CASCADE"
    ))
    private Status status;


    public Funcoes() {} // Construtor vazio obrigatório para JPA

    public Funcoes(Long id, String nome, Status status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
