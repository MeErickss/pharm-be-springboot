package com.example.pharm.model;

import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoElemento;
import jakarta.persistence.*;

@Entity
@Table(name="distribuicao_planta")
public class DistribuicaoPlanta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String nomePadronizado;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String posicaoNoLayout;

    @Enumerated(EnumType.STRING)
    private TipoElemento tipo;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "ponto_controle_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_parametro_pontocontrole"),
            nullable = true
    )
    private PontoControle pontoControle;

    public DistribuicaoPlanta(){}

    public DistribuicaoPlanta (Long id, String nomePadronizado, String endereco, TipoElemento tipo, StatusEnum statusEnum, PontoControle pontoControle){
        this.id = id;
        this.nomePadronizado = nomePadronizado;
        this.endereco = endereco;
        this. tipo = tipo;
        this.statusEnum = statusEnum;
        this.pontoControle = pontoControle;
    }

    public Long getId() {return id;}
    public StatusEnum getStatus() {return statusEnum;}
    public String getEndereco() {return endereco;}
    public String getNomePadronizado() {return nomePadronizado;}
    public String getPosicaoNoLayout() {return posicaoNoLayout;}
    public TipoElemento getTipo() {return tipo;}
    public StatusEnum getStatusEnum() {return statusEnum;}
    public String getNome() {return nome;}
    public PontoControle getPontoControle() {return pontoControle;}

    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setNomePadronizado(String nomePadronizado) {this.nomePadronizado = nomePadronizado;}
    public void setPosicaoNoLayout(String posicaoNoLayout) {this.posicaoNoLayout = posicaoNoLayout;}
    public void setStatus(StatusEnum statusEnum) {this.statusEnum = statusEnum;}
    public void setNome(String nome) {this.nome = nome;}
    public void setStatusEnum(StatusEnum statusEnum) {this.statusEnum = statusEnum;}
    public void setTipo(TipoElemento tipo) {this.tipo = tipo;}
    public void setPontoControle(PontoControle pontoControle) {this.pontoControle = pontoControle;}
}
