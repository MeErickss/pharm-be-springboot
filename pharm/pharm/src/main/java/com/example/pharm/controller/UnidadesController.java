package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.UnidadesService;

public class UnidadesController {
    private final UnidadesService unidadesService;

    public UnidadesController(UnidadesService unidadesService){
        this.unidadesService = unidadesService;
    }
}
