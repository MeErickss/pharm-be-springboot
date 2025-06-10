package com.example.pharm.controller;

import com.example.pharm.dto.LogArmazenamentoDto;
import com.example.pharm.model.LogAlarme;
import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.service.LogArmazenamentoService;
import com.example.pharm.service.TokenService;
import org.antlr.v4.runtime.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/logarmazenamento")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LogArmazenamentoController {
    private final LogArmazenamentoService logArmazenamentoService;
    private final TokenService tokenService;

    public LogArmazenamentoController(LogArmazenamentoService logArmazenamentoService, TokenService tokenService){
        this.logArmazenamentoService = logArmazenamentoService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<Page<LogArmazenamento>> listAll(
            @CookieValue(name = "JWT", required = false) String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) { // Alterado para ler do cookie

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<LogArmazenamento> pageResult = logArmazenamentoService.listAll(pageable);

        return ResponseEntity.ok(pageResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLogArmazenamento(@PathVariable Long id){
        logArmazenamentoService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogArmazenamento> autualizarLogAmazenamento(@PathVariable Long id,
                                                             @RequestBody LogArmazenamentoDto logArmazenamentoDto){
        LogArmazenamento atualizado = logArmazenamentoService.atualizar(id, logArmazenamentoDto);
        return ResponseEntity.ok(atualizado);
    }
}
