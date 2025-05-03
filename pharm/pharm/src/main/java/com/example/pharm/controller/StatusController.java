package com.example.pharm.controller;

import com.example.pharm.model.Status;
import com.example.pharm.repository.StatusRepository;
import com.example.pharm.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;
    private final StatusRepository statusRepository;

    public StatusController(StatusService statusService, StatusRepository statusRepository) {
        this.statusService = statusService;
        this.statusRepository = statusRepository;
    }

    /**
     * Cria um novo status.
     * Exemplo de chamada no Postman:
     * POST  http://localhost:8080/api/status
     * Body (JSON):
     * {
     *   "descricao": "ATIVO"
     * }
     */
    @PostMapping
    public ResponseEntity<Void> criarStatus(@RequestBody StatusDto dto) {
        statusService.criarStatus(dto.getDescricao());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Lista todos os status.
     * GET http://localhost:8080/api/status
     */
    @GetMapping
    public ResponseEntity<List<Status>> listAll() {
        List<Status> todos = statusService.listAll();
        return ResponseEntity.ok(todos);
    }


    /**
     * Busca um status por ID.
     * GET http://localhost:8080/api/status/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Status> buscarPorId(@PathVariable Long id) {
        Status findId = statusService.listId(id);
        return ResponseEntity.ok(findId);
    }
}

// DTO simples para criar
class StatusDto {
    private String descricao;
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
