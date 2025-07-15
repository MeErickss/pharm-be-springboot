package com.example.pharm.controller;


import com.example.pharm.dto.LogProducaoDto;
import com.example.pharm.dto.ParametroDto;
import com.example.pharm.dto.PontoControleDto;
import com.example.pharm.model.*;
import com.example.pharm.service.LogProducaoService;
import com.example.pharm.service.PontoControleService;
import com.example.pharm.service.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pontocontrole")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class PontoControleController {
    private final PontoControleService pontoControleService;
    private final TokenService tokenService;

    public PontoControleController(PontoControleService pontoControleService, TokenService tokenService){
        this.pontoControleService = pontoControleService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<PontoControle>> listAll(
            @CookieValue(name = "JWT", required = false) String token) { // Alterado para ler do cookie

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<PontoControle> pageResult = pontoControleService.listAll();

        return ResponseEntity.ok(pageResult);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPontoControle(@PathVariable Long id){
        pontoControleService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping
    public ResponseEntity<PontoControle> autualizarPontoControle(@RequestBody PontoControleDto dto,
                                                             @RequestParam String userLogin){
        PontoControle atualizado = pontoControleService.atualizarPontoControle(dto);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping
    public ResponseEntity<Void> inserirPontoControle(
            @RequestBody PontoControleDto dto,
            @CookieValue(name = "JWT", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userIdToken = tokenService.validarToken(token);
        if (userIdToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        pontoControleService.insertPontoControle(dto);


        return ResponseEntity.noContent().build();
    }
}
