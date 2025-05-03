package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.NiveisService;

public class NiveisController {
    private final NiveisService niveisService;

    public NiveisController(NiveisService niveisService){
        this.niveisService = niveisService;
    }
}

class NiveisDto{
    private String descricao;
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}