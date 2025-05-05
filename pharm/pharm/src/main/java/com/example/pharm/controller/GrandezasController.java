package com.example.pharm.controller;

import com.example.pharm.model.Grandeza;
import com.example.pharm.service.GrandezasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/grandezas")
public class GrandezasController {
    private final GrandezasService grandezasService;

    public GrandezasController(GrandezasService grandezasService){
        this.grandezasService = grandezasService;
    }

    @GetMapping
    public ResponseEntity<List<Grandeza>> listAll(){
        List<Grandeza> todos = grandezasService.listAll();
        return ResponseEntity.ok(todos);
    }
}
