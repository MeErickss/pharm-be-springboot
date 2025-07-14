package com.example.pharm.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {
    private final Map<String, Long> tokens = new ConcurrentHashMap<>();

    public String criarTokenParaUsuario(Long userId) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, userId);
        return token;
    }

    public Long validarToken(String token) {
        return tokens.get(token);
    }

    public void revogarToken(String token) {
        tokens.remove(token);
    }
}
