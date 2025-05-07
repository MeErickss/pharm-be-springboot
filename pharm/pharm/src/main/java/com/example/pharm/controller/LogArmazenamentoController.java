package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.Parametro;
import com.example.pharm.service.LogArmazenamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
