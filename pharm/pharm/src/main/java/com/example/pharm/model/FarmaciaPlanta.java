package com.example.pharm.model;

import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoElemento;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Modifying;

@Entity(name = "Farmacia")
@Table(name="farmacia_planta")
public class FarmaciaPlanta {
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
    @Column(name = "status_enum", nullable = false)
    private StatusEnum statusEnum;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "ponto_controle_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_parametro_pontocontrole"),
            nullable = true
    )
    private PontoControle pontoControle;

    public FarmaciaPlanta(){}

    public FarmaciaPlanta (Long id, String nomePadronizado, String endereco, TipoElemento tipo, StatusEnum statusEnum, PontoControle pontoControle){
        this.id = id;
        this.nomePadronizado = nomePadronizado;
        this.endereco = endereco;
        this. tipo = tipo;
        this.statusEnum = statusEnum;
        this.pontoControle = pontoControle;
    }

    public Long getId() {return id;}
    public String getNome() {return nome;}
    public StatusEnum getStatusEnum() {return statusEnum;}
    public void setNome(String nome) {this.nome = nome;}
    public void setStatusEnum(StatusEnum statusEnum) {this.statusEnum = statusEnum;}
    public StatusEnum getStatus() {return statusEnum;}
    public String getEndereco() {return endereco;}
    public String getNomePadronizado() {return nomePadronizado;}
    public String getPosicaoNoLayout() {return posicaoNoLayout;}
    public TipoElemento getTipo() {return tipo;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setNomePadronizado(String nomePadronizado) {this.nomePadronizado = nomePadronizado;}
    public void setPosicaoNoLayout(String posicaoNoLayout) {this.posicaoNoLayout = posicaoNoLayout;}
    public void setStatus(StatusEnum statusEnum) {this.statusEnum = statusEnum;}
    public void setTipo(TipoElemento tipo) {this.tipo = tipo;}
    public PontoControle getPontoControle() {return pontoControle;}
    public void setPontoControle(PontoControle pontoControle) {this.pontoControle = pontoControle;}
}
