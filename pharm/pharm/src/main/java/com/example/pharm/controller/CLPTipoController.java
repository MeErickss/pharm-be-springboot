package com.example.pharm.controller;

import com.example.pharm.model.enumeration.CLPTipoEnum;
import com.example.pharm.model.enumeration.TipoUsoEnum;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("api/clptipo")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CLPTipoController {
    private final TokenService tokenService;

    public CLPTipoController(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<CLPTipoEnum>> listAll(
            @CookieValue(name = "JWT", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(Arrays.asList(CLPTipoEnum.values()));
    }
}
