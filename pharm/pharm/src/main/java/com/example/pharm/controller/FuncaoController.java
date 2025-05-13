package com.example.pharm.controller;

import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/funcao")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FuncaoController {

    private final TokenService tokenService;

    public FuncaoController(TokenService tokenService){
    this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<FuncaoEnum>> listAll(
            @CookieValue(name = "JWT", required = false) String token) { // Alterado para ler do cookie

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(Arrays.asList(FuncaoEnum.values()));
    }
}
