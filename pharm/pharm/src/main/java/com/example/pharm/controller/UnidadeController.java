package com.example.pharm.controller;

import com.example.pharm.dto.UnidadeDto;
import com.example.pharm.dto.UnidadeOutDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.repository.UnidadeRepository;
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
    private final UnidadeRepository unidadeRepository;

    public UnidadeController(UnidadeService unidadeService, TokenService tokenService, UnidadeRepository unidadeRepository){
        this.unidadeService = unidadeService;
        this.tokenService = tokenService;
        this.unidadeRepository = unidadeRepository;
    }

    @GetMapping
    public ResponseEntity<List<UnidadeOutDto>> listAll(
            @CookieValue(name = "JWT", required = false) String token) { // Alterado para ler do cookie

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<UnidadeOutDto> todos = unidadeRepository.findAllOut();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/porgrandeza")
    public ResponseEntity<List<Unidade>> listarPorGrandeza(
            @RequestParam("descricao") String descricaoGrandeza,
            @CookieValue(name = "JWT", required = false) String token
    ) {
        if (token == null || tokenService.validarToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Unidade> unidades = unidadeRepository
                .findUnidadesByGrandezaDescricao(descricaoGrandeza);
        return ResponseEntity.ok(unidades);
    }

    @PostMapping
    public ResponseEntity<Unidade> criarUnidade(@RequestBody UnidadeDto dto) {
        unidadeService.insertUnidades(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUnidade(@PathVariable Long id){
        unidadeService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping
    public ResponseEntity<Unidade> atualizarUnidade(@RequestBody UnidadeDto unidadeDto){
        Unidade atualizado = unidadeService.atualizarUnidade(unidadeDto);
        return ResponseEntity.ok(atualizado);
    }
}

