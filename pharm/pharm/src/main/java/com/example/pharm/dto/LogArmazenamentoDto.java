package com.example.pharm.dto;

import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;


public class LogArmazenamentoDto {

    private Long id;
    private Long userId;
    private Long parametroId;
    private String dataHora;
    private StatusEnum status;

    public LogArmazenamentoDto(Long id, Long userId, Long parametroId, String dataHora, StatusEnum status) {
        this.id = id;
        this.userId = userId;
        this.parametroId = parametroId;
        this.dataHora = dataHora;
        this.status = status;
    }

    public StatusEnum getStatus() {return status;}

    public Long getParametro() {return parametroId;}

    public String getDataHora() {return dataHora;}

    public Long getUser() {return userId;}
}
