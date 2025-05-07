package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.service.ParametroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/parametro")

public class ParametroController {
    private final ParametroService parametroService;

    public ParametroController(ParametroService parametroService){
        this.parametroService = parametroService;
    }

    @GetMapping
    public ResponseEntity<List<Parametro>> listAll() {
        List<Parametro> todos = parametroService.listAll();
        return ResponseEntity.ok(todos);
    }


    /**
     * Busca um status por ID.
     * GET http://localhost:8080/api/status/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Parametro> buscarPorId(@PathVariable Long id) {
        Parametro findId = parametroService.listId(id);
        return ResponseEntity.ok(findId);
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

//curl -X POST "http://localhost:8080/api/parametro" -H "Content-Type: application/json" --data "{\"valor\":50,\"vlmin\":10,\"vlmax\":100,\"statusenum\":\"ATIVO\",\"descricao\":\"Parâmetro de teste\",\"unidadeId\":1,\"funcaoenum\":\"PRODUCAO\",\"grandezaId\":2}"
