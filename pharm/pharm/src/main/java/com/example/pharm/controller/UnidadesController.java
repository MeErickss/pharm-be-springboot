package com.example.pharm.controller;

import com.example.pharm.model.Unidade;
import com.example.pharm.service.UnidadesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/unidades")
public class UnidadesController {
    private final UnidadesService unidadesService;

    public UnidadesController(UnidadesService unidadesService){
        this.unidadesService = unidadesService;
    }

    @GetMapping
    public ResponseEntity<List<Unidade>> listAll(){
        List<Unidade> todos = unidadesService.listAll();
        return ResponseEntity.ok(todos);
    }
}

