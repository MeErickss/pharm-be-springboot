package com.example.pharm.model;

import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.engine.spi.Status;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parametro")
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    private Integer vlMin;

    @Column(nullable = false)
    private Integer vlMax;

    private StatusEnum status;

    @Column(nullable = false)
    private FuncaoEnum funcaoEnum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_grandeza", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_parametro_grandeza"))
    @JsonIgnoreProperties("parametros")    // permite serializar aqui
    private Grandeza grandeza;

    @ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_unidade", nullable = false)
    @JsonIgnoreProperties("parametros")    // permite serializar aqui
    private Unidade unidade;

    public Parametro() {} // Construtor vazio

    public Parametro(Long id, String descricao, Integer valor, Integer vlMin, Integer vlMax, StatusEnum status, Grandeza grandeza, Unidade unidade, FuncaoEnum funcaoEnum) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.vlMin = vlMin;
        this.vlMax = vlMax;
        this.status = status;
        this.grandeza = grandeza;
        this.unidade = unidade;
        this.funcaoEnum = funcaoEnum;
    }

    public Long getId() { return id; }
    public StatusEnum getStatus() { return status; }

    public void setValor(Integer valor) { this.valor = valor; }
    public void setVlMin(Integer vlMin) { this.vlMin = vlMin; }
    public void setVlMax(Integer vlMax) { this.vlMax = vlMax; }
    public void setStatus(StatusEnum status) { this.status = status; }

    public void setFuncao(FuncaoEnum funcaoEnum) {this.funcaoEnum = funcaoEnum;}

    public FuncaoEnum getFuncao() {return funcaoEnum;}

    public void setGrandeza(Grandeza grandeza) {this.grandeza = grandeza;}

    public Unidade getUnidade() { return unidade; }

    public void setUnidade(Unidade unidade) {this.unidade = unidade;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Grandeza getGrandeza() {return grandeza;}
    public Integer getValor() {return valor;}
    public Integer getVlMax() {return vlMax;}
    public Integer getVlMin() {return vlMin;}
}
