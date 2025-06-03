package com.example.pharm.dto;

import com.example.pharm.model.enumeration.StatusEnum;

public class LogAlarmeDto {

    private Long id;
    private String user;
    private String descricao;
    private String dataHora;
    private StatusEnum status;

    public LogAlarmeDto(Long id, String user, String descricao, String dataHora, StatusEnum status) {
        this.id = id;
        this.user = user;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.status = status;
    }

    public StatusEnum getStatus() {return status;}

    public String getDescricao() {return descricao;}

    public String getDataHora() {return dataHora;}

    public String getUser() {return user;}
}
