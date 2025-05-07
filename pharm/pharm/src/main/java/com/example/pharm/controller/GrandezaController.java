package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.Grandeza;
import com.example.pharm.model.Parametro;
import com.example.pharm.service.GrandezaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
