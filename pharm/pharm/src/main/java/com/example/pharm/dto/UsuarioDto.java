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

    public Long getId() {return id;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public NivelEnum getNivel() {return nivel;}
    public void setNivel(NivelEnum nivel) {this.nivel = nivel;}
    public StatusEnum getStatus() {return status;}
    public void setStatus(StatusEnum status) {this.status = status;}

}
