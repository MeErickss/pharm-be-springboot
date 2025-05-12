package com.example.pharm.controller;

import com.example.pharm.dto.LogAlarmeDto;
import com.example.pharm.model.LogAlarme;
import com.example.pharm.model.Parametro;
import com.example.pharm.service.LogAlarmeService;
import com.example.pharm.service.TokenService;
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


    @GetMapping("/logalarme")
    public ResponseEntity<List<LogAlarme>> listAll(
            @RequestHeader("Authorization") String authHeader) {

        // espera algo como "Bearer <token>"
        String token = authHeader.replace("Bearer ", "");
        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<LogAlarme> todos = logAlarmeService.listAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<LogAlarme> criarLogAlarme(@RequestBody LogAlarmeDto dto) {
        LogAlarme salvo = logAlarmeService.criarLogAlarmes(
                dto.getUser(),
                dto.getDescricao(),
                dto.getStatus(),
                dto.getDataHora()
        );
        return ResponseEntity.ok(salvo);
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

