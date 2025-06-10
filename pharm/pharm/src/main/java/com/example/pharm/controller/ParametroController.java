package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.dto.ParametroOutDto;
import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.repository.LogArmazenamentoRepository;
import com.example.pharm.repository.LogProducaoRepository;
import com.example.pharm.repository.ParametroRepository;
import com.example.pharm.service.LogArmazenamentoService;
import com.example.pharm.service.LogProducaoService;
import com.example.pharm.service.ParametroService;
import com.example.pharm.service.TokenService;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/parametro")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ParametroController {
    private final ParametroService parametroService;
    private final TokenService tokenService;
    private final ParametroRepository parametroRepository;
    private final LogArmazenamentoService logArmazenamentoService;
    private final LogProducaoService logProducaoService;

    public ParametroController(ParametroService parametroService, TokenService tokenService, ParametroRepository parametroRepository, LogArmazenamentoService logArmazenamentoService,LogProducaoService logProducaoService){
        this.parametroService = parametroService;
        this.tokenService = tokenService;
        this.parametroRepository = parametroRepository;
        this.logProducaoService = logProducaoService;
        this.logArmazenamentoService = logArmazenamentoService;
    }

    @GetMapping
    public ResponseEntity<List<ParametroOutDto>> listAll(
            @CookieValue(name = "JWT", required = false) String token,
            @RequestParam FuncaoEnum funcaoEnum) {


        if (token == null || tokenService.validarToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<ParametroOutDto> lista = parametroRepository.findAllOut(funcaoEnum);
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Parametro> buscarPorId(@PathVariable Long id) {
        Parametro findId = parametroService.listId(id);
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public ResponseEntity<Void> inserirParametro(
            @RequestParam String userLogin,
            @RequestBody ParametroDto dto,
            @CookieValue(name = "JWT", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userIdToken = tokenService.validarToken(token);
        if (userIdToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Parametro parametro = parametroService.insertParametro(dto);

        switch (dto.getFuncao()){
            case PRODUCAO -> logProducaoService.insertLogProducao(userLogin, parametro.getId());
            case ARMAZENAMENTO -> logArmazenamentoService.insertLogArmazenamento(userLogin, parametro.getId());
        }

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParametro(@PathVariable Long id) {
        parametroService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping
    public ResponseEntity<Parametro> autualizarParametro(@RequestBody ParametroDto parametroDto){
        Parametro atualizado = parametroService.atualizarParametro(parametroDto);
        return ResponseEntity.ok(atualizado);
    }

}

//curl -X POST "http://localhost:8080/api/parametro" -H "Content-Type: application/json" --data "{\"valor\":50,\"vlmin\":10,\"vlmax\":100,\"statusenum\":\"ATIVO\",\"descricao\":\"Parâmetro de teste\",\"unidadeId\":1,\"funcaoenum\":\"PRODUCAO\",\"grandezaId\":2}"
//curl -X DELETE http://localhost:8080/api/parametro/1
//curl -X PUT http://localhost:8080/api/parametro/1 \ -H "Content-Type: application/json" \ -d '{"valor":60,"vlmin":15,"vlmax":110,"statusenum":"INATIVO","descricao":"Atualizado","unidadeId":1,"funcaoenum":"SOMA","grandezaId":2}'