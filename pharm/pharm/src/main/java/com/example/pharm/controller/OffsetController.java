package com.example.pharm.controller;

import com.example.pharm.model.enumeration.OffsetEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("api/offset")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OffsetController {
    private final TokenService tokenService;

    public OffsetController(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<OffsetEnum>> listAll(
            @CookieValue(name = "JWT", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(Arrays.asList(OffsetEnum.values()));
    }
}
