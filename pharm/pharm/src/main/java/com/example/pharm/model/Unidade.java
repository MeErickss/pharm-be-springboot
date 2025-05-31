package com.example.pharm.model;

import com.example.pharm.model.enumeration.StatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.engine.spi.Status;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidade")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String abreviacao;

    @Column(nullable = false)
    private StatusEnum status;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Parametro> parametro = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_grandeza", nullable = false)
    @JsonBackReference  // evita loop na serialização
    private Grandeza grandeza;

    public Unidade() {}

    public Unidade(Long id, String unidade, String abreviacao, StatusEnum status) {
        this.id = id;
        this.descricao = unidade;
        this.abreviacao = abreviacao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnidade() {
        return descricao;
    }

    public void setUnidade(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviacao() {return abreviacao;}

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<Parametro> getParametro() {
        return parametro;
    }

    public void setParametro(List<Parametro> parametro) {this.parametro = parametro;}


    public Grandeza getGrandeza() {
        return grandeza;
    }

    public void setGrandeza(Grandeza grandeza) {this.grandeza = grandeza;}
}
