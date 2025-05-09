package com.example.pharm.controller;

import com.example.pharm.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> creds) {
        String login = creds.get("login");
        String senha = creds.get("senha");

        String token = usuarioService.autenticarEGerarToken(login, senha);

        // devolve um JSON simples { "token": "..." }
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
