package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.LogAlarme;
import com.example.pharm.model.Parametro;
import com.example.pharm.service.LogAlarmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/logalarme")
public class LogAlarmeController {
    private final LogAlarmeService logAlarmeService;

    public LogAlarmeController(LogAlarmeService logAlarmeService){
        this.logAlarmeService = logAlarmeService;
    }


    @GetMapping
    public ResponseEntity<List<LogAlarme>> listAll(){
        List<LogAlarme> todos = logAlarmeService.listAll();
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

