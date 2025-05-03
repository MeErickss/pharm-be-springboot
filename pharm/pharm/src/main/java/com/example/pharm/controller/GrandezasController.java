package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.GrandezasService;

public class GrandezasController {
    private final GrandezasService grandezasService;

    public GrandezasController(GrandezasService grandezasService){
        this.grandezasService = grandezasService;
    }
}

class GrandezasDto{
    private String descricao;
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
