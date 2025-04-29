package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.ParametrosService;

public class ParametrosController {
    private final ParametrosService parametrosService;

    public ParametrosController(ParametrosService parametrosService){
        this.parametrosService = parametrosService;
    }
}
