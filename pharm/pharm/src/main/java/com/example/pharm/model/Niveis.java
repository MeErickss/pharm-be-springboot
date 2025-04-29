package com.example.pharm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "niveis")
public class Niveis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Corrigido de @Colunm
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(
            name = "fk_status_niveis",
            foreignKeyDefinition = "FOREIGN KEY (status_descricao) REFERENCES STATUS(descricao) ON DELETE CASCADE"
    ))
    private Status status;

    public Niveis() {} // Construtor padrão obrigatório para JPA

    public Niveis(Long id, String descricao, Status status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
