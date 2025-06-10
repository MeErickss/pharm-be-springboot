package com.example.pharm.controller;

import com.example.pharm.model.Parametro;
import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/status")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class StatusController {
    private final TokenService tokenService;

    public StatusController(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<StatusEnum>> listAll(
        @CookieValue(name = "JWT", required = false) String token) { // Alterado para ler do cookie

            if (token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Long userId = tokenService.validarToken(token);
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return ResponseEntity.ok(Arrays.asList(StatusEnum.values()));
    }
}
