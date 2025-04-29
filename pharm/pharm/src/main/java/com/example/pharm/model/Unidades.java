package com.example.pharm.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "unidades")
public class Unidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Corrigido de @Colunm
    private String unidade;

    @Column(nullable = false)
    private String abreviacao;

    @ManyToOne
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(
            name = "fk_status_unidades",
            foreignKeyDefinition = "FOREIGN KEY (status_descricao) REFERENCES STATUS(descricao) ON DELETE CASCADE"
    ))
    private Status status;

    @ManyToMany(mappedBy = "unidades", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Parametros> parametros;

    @ManyToMany(mappedBy = "unidades", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Grandeza> grandezas;

    public Unidades() {} // Construtor vazio obrigatório

    public Unidades(Long id, String unidade, String abreviacao, Status status) {
        this.id = id;
        this.unidade = unidade;
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
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Parametros> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametros> parametros) {
        this.parametros = parametros;
    }

    public List<Grandeza> getGrandezas() {
        return grandezas;
    }

    public void setGrandezas(List<Grandeza> grandezas) {
        this.grandezas = grandezas;
    }
}
