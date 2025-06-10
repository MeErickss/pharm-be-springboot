package com.example.pharm.dto;

import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;

public class LogProducaoDto {

    private Long id;
    private Long userId;
    private Long parametroId;
    private String dataHora;
    private StatusEnum status;

    public LogProducaoDto(Long id, Long userId, Long parametroId, String dataHora, StatusEnum status) {
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
