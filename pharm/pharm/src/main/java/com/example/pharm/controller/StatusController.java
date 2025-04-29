package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.StatusService;

public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService){
        this.statusService = statusService;
    }
}
