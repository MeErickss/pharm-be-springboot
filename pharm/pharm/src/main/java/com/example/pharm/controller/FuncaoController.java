package com.example.pharm.controller;

import com.example.pharm.model.Parametro;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/funcao")
@CrossOrigin(origins = "http://localhost:5173")
public class FuncaoController {

    private final TokenService tokenService;

    public FuncaoController(TokenService tokenService){
    this.tokenService = tokenService;
    }

    @GetMapping("/funcao")
    public ResponseEntity<List<FuncaoEnum>> listAll(
            @RequestHeader("Authorization") String authHeader) {

        // espera algo como "Bearer <token>"
        String token = authHeader.replace("Bearer ", "");
        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(Arrays.asList(FuncaoEnum.values()));
    }
}
