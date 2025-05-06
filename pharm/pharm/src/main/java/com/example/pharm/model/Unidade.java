package com.example.pharm.model;

import com.example.pharm.model.enumeration.StatusEnum;
import jakarta.persistence.*;
import org.hibernate.engine.spi.Status;

import java.io.ObjectInputFilter;
import java.util.List;

@Entity
@Table(name = "unidades")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Corrigido de @Colunm
    private String descricao;

    @Column(nullable = false)
    private String abreviacao;

    @Column(nullable = false)
    private StatusEnum status;

    @ManyToMany(mappedBy = "unidades", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Parametro> parametros;

    @ManyToMany(mappedBy = "unidades", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Grandeza> grandezas;

    public Unidade() {} // Construtor vazio obrigatório

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

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    public List<Grandeza> getGrandezas() {
        return grandezas;
    }

    public void setGrandezas(List<Grandeza> grandezas) {
        this.grandezas = grandezas;
    }
}
