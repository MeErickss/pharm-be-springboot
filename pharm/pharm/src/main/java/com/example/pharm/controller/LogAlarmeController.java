package com.example.pharm.controller;

import com.example.pharm.model.LogAlarme;
import com.example.pharm.service.LogAlarmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/logalarme")
public class LogAlarmeController {
    private final LogAlarmeService logAlarmeService;

    public LogAlarmeController(LogAlarmeService logAlarmeService){
        this.logAlarmeService = logAlarmeService;
    }


    @GetMapping
    public ResponseEntity<List<LogAlarme>> listAll(){
        List<LogAlarme> todos = logAlarmeService.listAll();
        return ResponseEntity.ok(todos);
    }

}

