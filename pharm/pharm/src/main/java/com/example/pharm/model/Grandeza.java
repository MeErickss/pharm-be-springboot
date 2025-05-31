package com.example.pharm.model;

import com.example.pharm.model.enumeration.StatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import org.hibernate.engine.spi.Status;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grandeza")
public class Grandeza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Corrigido de @Colunm
    private String descricao;

    @Column(nullable = false)
    private StatusEnum status;

    @OneToMany(mappedBy = "grandeza", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Unidade> unidade = new ArrayList<>();


    @JsonIgnoreProperties("grandeza")
    @OneToMany(mappedBy = "grandeza", cascade = CascadeType.REMOVE)
    private List<Parametro> parametro = new ArrayList<>();

    public Grandeza() {} // Construtor vazio obrigatório para o JPA

    public Grandeza(Long id, String descricao, StatusEnum status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<Unidade> getUnidades() {
        return unidade;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidade = unidades;
    }

    public List<Parametro> getParametros() {
        return parametro;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametro = parametros;
    }
}
