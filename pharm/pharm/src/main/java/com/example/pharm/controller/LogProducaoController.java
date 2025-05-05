package com.example.pharm.controller;

import com.example.pharm.model.LogProducao;
import com.example.pharm.service.LogProducaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/logproducao")
public class LogProducaoController {
    private final LogProducaoService logProducaoService;

    public LogProducaoController(LogProducaoService logProducaoService){
        this.logProducaoService = logProducaoService;
    }

    @GetMapping
    public ResponseEntity<List<LogProducao>> listAll(){
        List<LogProducao> todos = logProducaoService.listAll();
        return ResponseEntity.ok(todos);
    }
}
