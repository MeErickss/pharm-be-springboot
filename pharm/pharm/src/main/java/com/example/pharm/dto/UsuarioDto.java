package com.example.pharm.dto;

import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;

public class UsuarioDto {

    private Long id;
    private String login;
    private String password;
    private StatusEnum status;
    private NivelEnum nivel;

    public UsuarioDto(Long id, String login, String password, StatusEnum status, NivelEnum nivel) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
        this.nivel = nivel;
    }
}
