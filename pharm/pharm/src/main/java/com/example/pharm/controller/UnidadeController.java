package com.example.pharm.controller;

import com.example.pharm.dto.UnidadeDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.service.TokenService;
import com.example.pharm.service.UnidadeService;
import org.hibernate.loader.NonUniqueDiscoveredSqlAliasException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/unidade")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UnidadeController {
    private final UnidadeService unidadeService;
    private final TokenService tokenService;

    public UnidadeController(UnidadeService unidadeService, TokenService tokenService){
        this.unidadeService = unidadeService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<Unidade>> listAll(
            @CookieValue(name = "JWT", required = false) String token) { // Alterado para ler do cookie

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Unidade> todos = unidadeService.listAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Unidade> criarUnidade(@RequestBody UnidadeDto dto) {
        Unidade salvo = unidadeService.criarUnidades(
                dto.getDescricao(),
                dto.getAbreviacao(),
                dto.getStatus()
        );
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUnidade(@PathVariable Long id){
        unidadeService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidade> autualizarUnidade(@PathVariable Long id,
                                                         @RequestBody UnidadeDto unidadeDto){
        Unidade atualizado = unidadeService.atualizar(id, unidadeDto);
        return ResponseEntity.ok(atualizado);
    }
}

