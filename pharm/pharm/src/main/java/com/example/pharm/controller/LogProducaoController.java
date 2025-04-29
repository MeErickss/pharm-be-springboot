package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.LogProducaoService;

public class LogProducaoController {
    private final LogProducaoService logProducaoService;

    public LogProducaoController(LogProducaoService logProducaoService){
        this.logProducaoService = logProducaoService;
    }
}
