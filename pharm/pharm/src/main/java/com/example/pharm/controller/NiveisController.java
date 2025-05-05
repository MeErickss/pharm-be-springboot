package com.example.pharm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/niveis")

public class NiveisController {
    private final NiveisService niveisService;

    public NiveisController(NiveisService niveisService){
        this.niveisService = niveisService;
    }

    @GetMapping
    public ResponseEntity<List<Niveis>> listAll() {
        List<Niveis> todos = niveisService.listAll();
        return ResponseEntity.ok(todos);
    }

}
