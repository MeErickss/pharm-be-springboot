package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.LogProducaoService;

public class LogProducaoController {
    private final LogProducaoService logProducaoService;

    public LogProducaoController(LogProducaoService logProducaoService){
        this.logProducaoService = logProducaoService;
    }
}

class LogProducaoDto{
    private String descricao;
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}