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
import lombok.extern.java.Log;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
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

    @PutMapping
    public ResponseEntity<Void> atualizarParametro(
            @RequestBody ParametroDto dto,
            @RequestParam String userLogin
    ) {
        Parametro antes = parametroRepository.findById(dto.getId()).orElseThrow();

        switch (dto.getFuncao()) {
            case PRODUCAO ->
                    logProducaoService.insertLogProducao(userLogin, dto.getId(), antes);
            case ARMAZENAMENTO ->
                    logArmazenamentoService.insertLogArmazenamento(userLogin, dto.getId(), antes);
        }

        // 3) Agora faz a atualização efetiva
        parametroService.atualizarParametro(dto);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParametro(@PathVariable Long id) {
        parametroService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}

