package com.example.pharm.dto;

import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

public class GrandezaDto {

    private Long id;
    private String descricao;
    private StatusEnum status;
    private Long unidadeId;

    public GrandezaDto(Long id, String descricao, StatusEnum status, Long unidadeId){
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.unidadeId = unidadeId;
    }

    public Long getId() {return id;}

    public StatusEnum getStatus() {return status;}

    public String getDescricao() {return descricao;}

    public Long getUnidadeId() {return unidadeId;}
}
