package com.example.pharm.controller;

import com.example.pharm.dto.LogAlarmeDto;
import com.example.pharm.model.LogAlarme;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.service.LogAlarmeService;
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
@RequestMapping("api/logalarme")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LogAlarmeController {
    private final LogAlarmeService logAlarmeService;
    private final TokenService tokenService;

    public LogAlarmeController(LogAlarmeService logAlarmeService, TokenService tokenService){
        this.logAlarmeService = logAlarmeService;
        this.tokenService = tokenService;
    }


    @GetMapping
    public ResponseEntity<Page<LogAlarme>> listAll(
            @CookieValue(name = "JWT", required = false) String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<LogAlarme> pageResult = logAlarmeService.listAll(pageable);

        return ResponseEntity.ok(pageResult);
    }


    @PostMapping
    public ResponseEntity<LogAlarme> criarLogAlarme(@RequestBody LogAlarmeDto dto) {
        logAlarmeService.insertLogAlarmes(dto);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLogAlarme(@PathVariable Long id){
        logAlarmeService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogAlarme> autualizarLogAlarme(@PathVariable Long id,
                                                             @RequestBody LogAlarmeDto logAlarmeDto){
        LogAlarme atualizado = logAlarmeService.atualizar(id, logAlarmeDto);
        return ResponseEntity.ok(atualizado);
    }
}

