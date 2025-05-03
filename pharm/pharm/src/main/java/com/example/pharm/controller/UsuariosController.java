package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.UsuariosService;

public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService){
        this.usuariosService = usuariosService;
    }


}

class UsuariosDto{
    private String descricao;
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}