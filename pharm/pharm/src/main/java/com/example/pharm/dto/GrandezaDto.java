package com.example.pharm.dto;

import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

public class GrandezaDto {

    private String descricao;
    private StatusEnum status;
    private Long unidadeId;

    public GrandezaDto(String descricao, StatusEnum status, Long unidadeId){
        this.descricao = descricao;
        this.status = status;
        this.unidadeId = unidadeId;
    }
}
