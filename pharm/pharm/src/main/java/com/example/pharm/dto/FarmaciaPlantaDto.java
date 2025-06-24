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

    public String getPosicaoNoLayout() {return posicaoNoLayout;}
    public String getEndereco() {return endereco;}
    public StatusEnum getStatusEnum() {return statusEnum;}
    public String getNomePadronizado() {return nomePadronizado;}
    public Long getId() {return id;}
    public TipoElemento getTipo() {return tipo;}
}
