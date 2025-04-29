package com.example.pharm.controller;

import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.LogAlarmesService;

public class LogAlarmesController {
    private final LogAlarmesService logAlarmesService;

    public LogAlarmesController(LogAlarmesService logAlarmesService){
        this.logAlarmesService = logAlarmesService;
    }
}
