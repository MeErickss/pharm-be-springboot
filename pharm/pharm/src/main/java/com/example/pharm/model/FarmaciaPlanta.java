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

    @Column(nullable = false, unique = true)
    private String nomePadronizado; // ex: "VALVULA_V1", "BOMBA_B3", "SENSOR_TEMP_01"

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String posicaoNoLayout; // opcional: ex: "X:120,Y:300" ou ID do SVG

    @Enumerated(EnumType.STRING)
    private TipoElemento tipo; // Enum: VALVULA, SENSOR, BOMBA, INDICADOR_VOLUME, etc.

    @Enumerated(EnumType.STRING)
    @Column(name = "status_enum", nullable = false)
    private StatusEnum statusEnum;

    public FarmaciaPlanta(){}

    public FarmaciaPlanta (Long id, String nomePadronizado, String endereco, TipoElemento tipo, StatusEnum statusEnum){
        this.id = id;
        this.nomePadronizado = nomePadronizado;
        this.endereco = endereco;
        this. tipo = tipo;
        this.statusEnum = statusEnum;
    }

    public Long getId() {return id;}
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
}
