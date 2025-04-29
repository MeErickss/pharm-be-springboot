package com.example.pharm.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parametros")
public class Parametros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String parametro;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    private Integer vlMin;

    @Column(nullable = false)
    private Integer vlMax;

    @ManyToOne
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(
            name = "fk_status_parametros",
            foreignKeyDefinition = "FOREIGN KEY (status_descricao) REFERENCES STATUS(descricao) ON DELETE CASCADE"
    ))
    private Status status;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "parametros_funcoes",
            joinColumns = @JoinColumn(name = "id_parametros"),
            inverseJoinColumns = @JoinColumn(name = "id_funcoes")
    )
    private List<Funcoes> funcoes = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "parametros_grandeza",
            joinColumns = @JoinColumn(name = "id_parametros"),
            inverseJoinColumns = @JoinColumn(name = "id_grandeza")
    )
    private List<Grandeza> grandeza = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "parametros_unidades",
            joinColumns = @JoinColumn(name = "id_parametros"),
            inverseJoinColumns = @JoinColumn(name = "id_unidades")
    )
    private List<Unidades> unidades = new ArrayList<>();

    public Parametros() {} // Construtor vazio

    public Parametros(Long id, String parametro, Integer valor, Integer vlMin, Integer vlMax, Status status) {
        this.id = id;
        this.parametro = parametro;
        this.valor = valor;
        this.vlMin = vlMin;
        this.vlMax = vlMax;
        this.status = status;
    }

    public Long getId() { return id; }
    public Status getStatus() { return status; }

    public void setParametro(String parametro) { this.parametro = parametro; }
    public void setValor(Integer valor) { this.valor = valor; }
    public void setVlMin(Integer vlMin) { this.vlMin = vlMin; }
    public void setVlMax(Integer vlMax) { this.vlMax = vlMax; }
    public void setStatus(Status status) { this.status = status; }

    public List<Funcoes> getFuncoes() { return funcoes; }
    public void setFuncoes(List<Funcoes> funcoes) { this.funcoes = funcoes; }

    public List<Grandeza> getGrandeza() { return grandeza; }
    public void setGrandeza(List<Grandeza> grandeza) { this.grandeza = grandeza; }

    public List<Unidades> getUnidades() { return unidades; }
    public void setUnidades(List<Unidades> unidades) { this.unidades = unidades; }
}
