package com.example.pharm.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {
    // mapa simples token -> userId; para prod você usaria um repositório/tabela
    private final Map<String, Long> tokens = new ConcurrentHashMap<>();

    /** Gera e armazena um token */
    public String criarTokenParaUsuario(Long userId) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, userId);
        return token;
    }

    /** Retorna o userId associado ou null se inválido */
    public Long validarToken(String token) {
        return tokens.get(token);
    }

    /** (Opcional) Para logout: remove o token */
    public void revogarToken(String token) {
        tokens.remove(token);
    }
}
