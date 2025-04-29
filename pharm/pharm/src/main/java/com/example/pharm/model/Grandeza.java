package com.example.pharm.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "grandeza")
public class Grandeza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Corrigido de @Colunm
    private String nome;

    @ManyToOne
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(
            name = "fk_status_grandeza",
            foreignKeyDefinition = "FOREIGN KEY (status_descricao) REFERENCES STATUS(descricao) ON DELETE CASCADE"
    ))
    private Status status;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "grandeza_unidades",
            joinColumns = @JoinColumn(name = "id_grandeza"),
            inverseJoinColumns = @JoinColumn(name = "id_unidades")
    )
    private List<Unidades> unidades;

    @ManyToMany(mappedBy = "grandeza", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Parametros> parametros;

    public Grandeza() {} // Construtor vazio obrigatório para o JPA

    public Grandeza(Long id, String nome, Status status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Unidades> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidades> unidades) {
        this.unidades = unidades;
    }

    public List<Parametros> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametros> parametros) {
        this.parametros = parametros;
    }
}
