package com.example.pharm.controller;

import com.example.pharm.model.Grandeza;
import com.example.pharm.service.GrandezaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/grandeza")
public class GrandezaController {
    private final GrandezaService grandezaService;

    public GrandezaController(GrandezaService grandezaService){
        this.grandezaService = grandezaService;
    }

    @GetMapping
    public ResponseEntity<List<Grandeza>> listAll(){
        List<Grandeza> todos = grandezaService.listAll();
        return ResponseEntity.ok(todos);
    }
}
