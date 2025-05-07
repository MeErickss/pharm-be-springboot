package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Parametro;
import com.example.pharm.service.LogProducaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
