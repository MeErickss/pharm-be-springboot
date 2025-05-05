package com.example.pharm.controller;

import com.example.pharm.model.LogAlarme;
import com.example.pharm.service.LogAlarmesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/logalarmes")
public class LogAlarmesController {
    private final LogAlarmesService logAlarmesService;

    public LogAlarmesController(LogAlarmesService logAlarmesService){
        this.logAlarmesService = logAlarmesService;
    }


    @GetMapping
    public ResponseEntity<List<LogAlarme>> listAll(){
        List<LogAlarme> todos = logAlarmesService.listAll();
        return ResponseEntity.ok(todos);
    }

}

