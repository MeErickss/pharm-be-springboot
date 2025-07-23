package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.dto.ParametroOutDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.enumeration.FormulaEnum;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.repository.ParametroRepository;
import com.example.pharm.service.*;
import com.example.pharm.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/parametro")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ParametroController {
    private final ParametroService parametroService;
    private final TokenService tokenService;
    private final ParametroRepository parametroRepository;
    private final LogArmazenamentoService logArmazenamentoService;
    private final LogProducaoService logProducaoService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public ParametroController(ParametroService parametroService, TokenService tokenService, ParametroRepository parametroRepository, LogArmazenamentoService logArmazenamentoService,LogProducaoService logProducaoService, EmailService emailService, ObjectMapper objectMapper){
        this.parametroService = parametroService;
        this.tokenService = tokenService;
        this.parametroRepository = parametroRepository;
        this.logProducaoService = logProducaoService;
        this.logArmazenamentoService = logArmazenamentoService;
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<ParametroOutDto>> listAll(
            @CookieValue(name = "JWT", required = false) String token,
            @RequestParam FuncaoEnum funcaoEnum) {


        if (token == null || tokenService.validarToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<ParametroOutDto> lista = parametroRepository.findAllOut(funcaoEnum);
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Parametro> buscarPorId(@PathVariable Long id) {
        Parametro findId = parametroService.listId(id);
        return ResponseEntity.ok(findId);
    }

    @GetMapping("/formula")
    public ResponseEntity<Page<Parametro>> buscarPorFormula(
            @CookieValue(name = "JWT", required = false) String token,
            @RequestParam FormulaEnum formulaEnum,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        Sort.Direction dir = Sort.Direction.fromString(sort[1]);
        PageRequest pageReq = PageRequest.of(page, size, dir, sort[0]);
        Page<Parametro> result = parametroRepository.findByFormulaEnum(formulaEnum, pageReq);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> inserirParametro(
            @RequestParam String userLogin,
            @RequestBody ParametroDto dto,
            @CookieValue(name = "JWT", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userIdToken = tokenService.validarToken(token);
        if (userIdToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Parametro parametro = parametroService.insertParametro(dto);

        switch (dto.getFuncao()){
            case PRODUCAO -> logProducaoService.insertLogProducao(userLogin, parametro.getId(), "Insert Parâmetro Produção "+userLogin);
            case ARMAZENAMENTO -> logArmazenamentoService.insertLogArmazenamento(userLogin, parametro.getId(), "Insert Parâmetro Armazenamento "+userLogin);
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarParametro(
            @RequestBody ParametroDto dto,
            @RequestParam String userLogin
    ) throws JsonProcessingException {
        // pega a entidade antiga e converte pra DTO
        Parametro antes = parametroRepository.findById(dto.getId()).orElseThrow();
        ParametroDto oldDto = ParametroDto.fromEntity(antes);

        // gera os JsonNode
        JsonNode oldNode = objectMapper.valueToTree(oldDto);
        JsonNode newNode = objectMapper.valueToTree(dto);

        // 1) Detecta alterações de forma genérica
        List<String> camposAlterados = parametroService.detectarAlteracoes(oldDto, dto);

        // 2) Para cada campo, lê valor antigo e novo
        String oldValues = camposAlterados.stream()
                .map(f -> f + "=" + nodeAsText(oldNode, f))
                .collect(Collectors.joining(", "));
        String newValues = camposAlterados.stream()
                .map(f -> f + "=" + nodeAsText(newNode, f))
                .collect(Collectors.joining(", "));

        // 3) Prepara texto de alterações
        String alteracoes = camposAlterados.isEmpty()
                ? "nenhum campo alterado"
                : String.join(", ", camposAlterados);

        if (emailService.isInternetAvailable()) {
            try {
                emailService.sendSimpleEmail(
                        "panicogamer64@gmail.com",
                        "HealthSafe Farmacia",
                        "O parâmetro: " + dto.getDescricao() + " foi atualizado com sucesso.\n" +
                                "Campos alterados: " + alteracoes + "\n" +
                                "Antes: " + oldValues + "\n" +
                                "Depois: " + newValues
                );
            } catch (MailException mex) {
                System.out.println("Não foi possível enviar o e‑mail de notificação" + mex);
            }
        } else {
            System.out.println("Usuário sem conexão: pulando envio de e‑mail");
        }

        switch (dto.getFuncao()) {
            case PRODUCAO ->
                    logProducaoService.insertLogProducao(userLogin, dto.getId(), antes, "Update Parâmetro Produção "+userLogin);
            case ARMAZENAMENTO ->
                    logArmazenamentoService.insertLogArmazenamento(userLogin, dto.getId(), antes, "Update Parâmetro Armazenamento "+userLogin);
        }

        parametroService.atualizarParametro(dto);
        return ResponseEntity.noContent().build();
    }

    private String nodeAsText(JsonNode node, String fieldName) {
        JsonNode f = node.get(fieldName);
        return f == null || f.isNull() ? "null" : f.asText();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParametro(@PathVariable Long id) {
        parametroService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}

