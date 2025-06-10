package com.example.pharm.dto;

import com.example.pharm.model.enumeration.StatusEnum;

public class UnidadeOutDto {
    private Long id;
    private String unidade;
    private String abreviacao;
    private StatusEnum status;
    private String grandeza;

    public UnidadeOutDto(
            Long id,
            String unidade,
            String abreviacao,
            StatusEnum status,
            String grandeza
    ) {
        this.id = id;
        this.unidade = unidade;
        this.abreviacao = abreviacao;
        this.status = status;
        this.grandeza = grandeza;
    }

    public Long getId() {
        return id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public String getGrandeza() {
        return grandeza;
    }
}
