package com.example.pharm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/funcoes")
public class FuncoesController {
    private final FuncoesService funcoesService;

    public FuncoesController(FuncoesService funcoesService){
        this.funcoesService = funcoesService;
    }

    @GetMapping
    public ResponseEntity<List<Funcoes>> listAll(){
        List<Funcoes> todos = funcoesService.listAll();
        return ResponseEntity.ok(todos);
    }
}
