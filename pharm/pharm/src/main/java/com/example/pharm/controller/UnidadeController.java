package com.example.pharm.controller;

import com.example.pharm.dto.UnidadeDto;
import com.example.pharm.dto.UnidadeOutDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.repository.UnidadeRepository;
import com.example.pharm.service.EmailService;
import com.example.pharm.service.TokenService;
import com.example.pharm.service.UnidadeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.loader.NonUniqueDiscoveredSqlAliasException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/unidade")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UnidadeController {
    private final UnidadeService unidadeService;
    private final TokenService tokenService;
    private final UnidadeRepository unidadeRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public UnidadeController(UnidadeService unidadeService, TokenService tokenService, UnidadeRepository unidadeRepository, ObjectMapper objectMapper, EmailService emailService){
        this.unidadeService = unidadeService;
        this.tokenService = tokenService;
        this.unidadeRepository = unidadeRepository;
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<UnidadeOutDto>> listAll(
            @CookieValue(name = "JWT", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<UnidadeOutDto> todos = unidadeRepository.findAllOut();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/porgrandeza")
    public ResponseEntity<List<Unidade>> listarPorGrandeza(
            @RequestParam("descricao") String descricaoGrandeza,
            @CookieValue(name = "JWT", required = false) String token
    ) {
        if (token == null || tokenService.validarToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Unidade> unidades = unidadeRepository
                .findUnidadesByGrandezaDescricao(descricaoGrandeza);
        return ResponseEntity.ok(unidades);
    }

    @PostMapping
    public ResponseEntity<Unidade> criarUnidade(@RequestBody UnidadeDto dto) {
        unidadeService.insertUnidades(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUnidade(@PathVariable Long id){
        unidadeService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping
    public ResponseEntity<Unidade> atualizarUnidade(
            @RequestBody UnidadeDto unidadeDto,    // DTO contém o id
            @RequestParam String userLogin
    ) throws JsonProcessingException {
        Long id = unidadeDto.getId();
        Unidade antes = unidadeService.listId(id);
        UnidadeDto oldDto = UnidadeDto.fromEntity(antes);

        JsonNode oldNode = objectMapper.valueToTree(oldDto);
        JsonNode newNode = objectMapper.valueToTree(unidadeDto);

        List<String> camposAlterados = unidadeService
                .detectarAlteracoes(oldDto, unidadeDto);

        String oldValues = camposAlterados.stream()
                .map(f -> f + "=" + unidadeService.nodeAsText(oldNode, f))
                .collect(Collectors.joining(", "));
        String newValues = camposAlterados.stream()
                .map(f -> f + "=" + unidadeService.nodeAsText(newNode, f))
                .collect(Collectors.joining(", "));
        String alteracoes = camposAlterados.isEmpty()
                ? "nenhum campo alterado"
                : String.join(", ", camposAlterados);

        Unidade atualizado = unidadeService.atualizarUnidade(unidadeDto);

        emailService.sendSimpleEmail(
                "panicogamer64@gmail.com",
                "Health Safe Farmacia - Unidade Atualizada",
                "Unidade atualizada: " + unidadeDto.getUnidade() + "\n" +
                        "Campos alterados: " + alteracoes + "\n" +
                        "Antes: " + oldValues + "\n" +
                        "Depois: " + newValues
        );

        return ResponseEntity.ok(atualizado);
    }


}

