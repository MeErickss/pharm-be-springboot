package com.example.pharm.model;

import com.example.pharm.model.enumeration.FormulaEnum;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormulaEnum formulaEnum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_grandeza", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_parametro_grandeza"))
    @JsonIgnoreProperties("parametros")
    private Grandeza grandeza;

    @ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_unidade", nullable = false)
    @JsonIgnoreProperties("parametros")
    private Unidade unidade;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "ponto_controle_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_parametro_pontocontrole"),
            nullable = true
    )
    private PontoControle pontoControle;

    public Parametro() {}

    public Parametro(Long id, String descricao, Integer valor, Integer vlMin, Integer vlMax, StatusEnum status, Grandeza grandeza, Unidade unidade, FuncaoEnum funcaoEnum, FormulaEnum formulaEnum, PontoControle pontoControle) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.vlMin = vlMin;
        this.vlMax = vlMax;
        this.status = status;
        this.grandeza = grandeza;
        this.unidade = unidade;
        this.funcaoEnum = funcaoEnum;
        this.formulaEnum = formulaEnum;
        this.pontoControle = pontoControle;
    }

    public Long getId() { return id; }
    public StatusEnum getStatus() { return status; }
    public void setGrandeza(Grandeza grandeza) {this.grandeza = grandeza;}
    public FuncaoEnum getFuncao() {return funcaoEnum;}
    public Unidade getUnidade() { return unidade; }
    public String getDescricao() {return descricao;}
    public Grandeza getGrandeza() {return grandeza;}
    public Integer getValor() {return valor;}
    public Integer getVlMax() {return vlMax;}
    public Integer getVlMin() {return vlMin;}
    public FormulaEnum getFormulaEnum() {return formulaEnum;}
    public PontoControle getPontoControle() {return pontoControle;}

    public void setFormulaEnum(FormulaEnum formulaEnum) {this.formulaEnum = formulaEnum;}
    public void setValor(Integer valor) { this.valor = valor; }
    public void setVlMin(Integer vlMin) { this.vlMin = vlMin; }
    public void setVlMax(Integer vlMax) { this.vlMax = vlMax; }
    public void setStatus(StatusEnum status) { this.status = status; }
    public void setFuncao(FuncaoEnum funcaoEnum) {this.funcaoEnum = funcaoEnum;}
    public void setUnidade(Unidade unidade) {this.unidade = unidade;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public void setPontoControle(PontoControle pontoControle) {this.pontoControle = pontoControle;}
}
