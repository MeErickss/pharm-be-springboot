package com.example.pharm.controller;

import com.example.pharm.dto.LogProducaoDto;
import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.service.LogProducaoService;
import com.example.pharm.service.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/logproducao")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LogProducaoController {
    private final LogProducaoService logProducaoService;
    private final TokenService tokenService;

    public LogProducaoController(LogProducaoService logProducaoService, TokenService tokenService){
        this.logProducaoService = logProducaoService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<Page<LogProducao>> listAll(
            @CookieValue(name = "JWT", required = false) String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) { // Alterado para ler do cookie

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<LogProducao> pageResult = logProducaoService.listAll(pageable);

        return ResponseEntity.ok(pageResult);
    }


    @PostMapping
    public ResponseEntity<LogProducao> criarLogProducao(@RequestBody LogProducaoDto dto) {
        LogProducao salvo = logProducaoService.criarLogProducao(
                dto.getUser(),
                dto.getDescricao(),
                dto.getStatus(),
                dto.getDataHora()
        );
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLogProducao(@PathVariable Long id){
        logProducaoService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogProducao> autualizarLogProducao(@PathVariable Long id,
                                                     @RequestBody LogProducaoDto logProducao){
        LogProducao atualizado = logProducaoService.atualizar(id, logProducao);
        return ResponseEntity.ok(atualizado);
    }
}
