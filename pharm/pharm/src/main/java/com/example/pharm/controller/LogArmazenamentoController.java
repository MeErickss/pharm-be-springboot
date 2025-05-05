package com.example.pharm.controller;

import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.service.LogArmazenamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/logarmazenamento")
public class LogArmazenamentoController {
    private final LogArmazenamentoService logArmazenamentoService;

    public LogArmazenamentoController(LogArmazenamentoService logArmazenamentoService){
        this.logArmazenamentoService = logArmazenamentoService;
    }

    @GetMapping
    public ResponseEntity<List<LogArmazenamento>> listAll(){
        List<LogArmazenamento> todos = logArmazenamentoService.listAll();
        return ResponseEntity.ok(todos);
    }
}
