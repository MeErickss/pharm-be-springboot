package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;

public class FuncoesController {
    private final FuncoesService funcoesService;

    public FuncoesController(FuncoesService funcoesService){
        this.funcoesService = funcoesService;
    }
}
