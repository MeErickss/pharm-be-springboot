package com.example.pharm.controller;

import com.example.pharm.model.Parametros;
import com.example.pharm.model.Status;
import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.ParametrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ParametrosController {
    private final ParametrosService parametrosService;

    public ParametrosController(ParametrosService parametrosService){
        this.parametrosService = parametrosService;
    }

    @GetMapping
    public ResponseEntity<List<Parametros>> listAll() {
        List<Parametros> todos = parametrosService.listAll();
        return ResponseEntity.ok(todos);
    }


    /**
     * Busca um status por ID.
     * GET http://localhost:8080/api/status/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Parametros> buscarPorId(@PathVariable Long id) {
        Parametros findId = parametrosService.listId(id);
        return ResponseEntity.ok(findId);
    }
}

class ParametrosDto{
    private String descricao;
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
