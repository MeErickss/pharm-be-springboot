package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.service.UnidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/unidade")
public class UnidadeController {
    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService){
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Unidade>> listAll(){
        List<Unidade> todos = unidadeService.listAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Parametro> criarParametro(@RequestBody ParametroDto dto) {
        Parametro salvo = parametroService.criarParametro(
                dto.getDescricao(),
                dto.getVlmin(),
                dto.getVlmax(),
                dto.getValor(),
                dto.getStatusenum(),
                dto.getGrandezaId(),
                dto.getUnidadeId(),
                dto.getFuncaoenum()
        );
        return ResponseEntity.ok(salvo);
    }
}

