package com.example.pharm.controller;

import com.example.pharm.dto.GrandezaDto;
import com.example.pharm.model.Grandeza;
import com.example.pharm.model.Parametro;
import com.example.pharm.service.GrandezaService;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grandeza")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class GrandezaController {
    private final GrandezaService grandezaService;
    private final TokenService tokenService;

    public GrandezaController(GrandezaService grandezaService, TokenService tokenService){
        this.grandezaService = grandezaService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<Grandeza>> listAll(
            @CookieValue(name = "JWT", required = false) String token) { // Alterado para ler do cookie

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Grandeza> todos = grandezaService.listAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Grandeza> criarGrandeza(@RequestBody GrandezaDto dto) {
        Grandeza salvo = grandezaService.criarGrandeza(
                dto.getDescricao(),
                dto.getStatus()
        );
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGrandeza(@PathVariable Long id){
        grandezaService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grandeza> autualizarGrandeza(@PathVariable Long id,
                                                             @RequestBody GrandezaDto grandezaDto){
        Grandeza atualizado = grandezaService.atualizar(id, grandezaDto);
        return ResponseEntity.ok(atualizado);
    }
}
