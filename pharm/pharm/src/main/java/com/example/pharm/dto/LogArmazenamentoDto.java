package com.example.pharm.dto;

import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;

public class LogArmazenamentoDto {

    private Long id;
    private Long userId;
    private String descricao;
    private String dataHora;
    private StatusEnum status;

    public LogArmazenamentoDto(Long id, Long userId, String descricao, String dataHora, StatusEnum status) {
        this.id = id;
        this.userId = userId;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.status = status;
    }

    public StatusEnum getStatus() {return status;}

    public String getDescricao() {return descricao;}

    public String getDataHora() {return dataHora;}

    public Long getUser() {return userId;}
}
