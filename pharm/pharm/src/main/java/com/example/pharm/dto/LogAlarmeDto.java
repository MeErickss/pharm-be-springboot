package com.example.pharm.dto;

import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;

public class LogAlarmeDto {

    private Long id;
    private String userLogin;
    private String descricao;
    private String dataHora;
    private StatusEnum status;

    public LogAlarmeDto(Long id, String userLogin, String descricao, String dataHora, StatusEnum status) {
        this.id = id;
        this.userLogin = userLogin;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.status = status;
    }

    public StatusEnum getStatus() {return status;}

    public String getDescricao() {return descricao;}

    public String getDataHora() {return dataHora;}

    public String getUser() {return userLogin;}
}
