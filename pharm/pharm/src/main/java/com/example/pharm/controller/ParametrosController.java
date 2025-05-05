package com.example.pharm.controller;

import com.example.pharm.dto.ParametrosDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Status;
import com.example.pharm.service.ParametrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/parametros")

public class ParametrosController {
    private final ParametrosService parametrosService;

    public ParametrosController(ParametrosService parametrosService){
        this.parametrosService = parametrosService;
    }

    @GetMapping
    public ResponseEntity<List<Parametro>> listAll() {
        List<Parametro> todos = parametrosService.listAll();
        List<ParametrosDto> dtos = todos.stream()
                .map(Parametro::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(todos);
    }


    /**
     * Busca um status por ID.
     * GET http://localhost:8080/api/status/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Parametro> buscarPorId(@PathVariable Long id) {
        Parametro findId = parametrosService.listId(id);
        return ResponseEntity.ok(findId);
    }
}
