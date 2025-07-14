package com.example.pharm.dto;

import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoElemento;

public class FarmaciaPlantaDto {
    private Long id;
    private String nomePadronizado;
    private String endereco;
    private TipoElemento tipo;
    private String posicaoNoLayout;
    private StatusEnum statusEnum;
    private String pontoControle;


    public FarmaciaPlantaDto(){}

    public FarmaciaPlantaDto(Long id, String nomePadronizado, String endereco, TipoElemento tipo, String posicaoNoLayout, StatusEnum statusEnum, String pontoControle){
        this.id = id;
        this.nomePadronizado = nomePadronizado;
        this.endereco = endereco;
        this.tipo = tipo;
        this.posicaoNoLayout = posicaoNoLayout;
        this.statusEnum = statusEnum;
        this.pontoControle = pontoControle;
    }

    public String getPosicaoNoLayout() {return posicaoNoLayout;}
    public String getEndereco() {return endereco;}
    public StatusEnum getStatusEnum() {return statusEnum;}
    public String getNomePadronizado() {return nomePadronizado;}
    public Long getId() {return id;}
    public TipoElemento getTipo() {return tipo;}
    public String getPontoControle() {return pontoControle;}
}
