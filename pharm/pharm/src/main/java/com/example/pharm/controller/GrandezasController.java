package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.GrandezasService;

public class GrandezasController {
    private final GrandezasService grandezasService;

    public GrandezasController(GrandezasService grandezasService){
        this.grandezasService = grandezasService;
    }
}
