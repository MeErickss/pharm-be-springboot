package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.LogArmazenamentoService;

public class LogArmazenamentoController {
    private final LogArmazenamentoService logArmazenamentoService;

    public LogArmazenamentoController(LogArmazenamentoService logArmazenamentoService){
        this.logArmazenamentoService = logArmazenamentoService;
    }
}

class LogArmazenentoDto{
    private String descricao;
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}