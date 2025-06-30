package com.example.pharm.controller;

import com.example.pharm.dto.LoginRequestDto;
import com.example.pharm.dto.UsuarioDto;
import com.example.pharm.model.Usuario;
import com.example.pharm.service.EmailService;
import com.example.pharm.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true",
        allowedHeaders = "*",   // libera Content-Type, Authorization, etc
        methods = {            // inclui OPTIONS para o preflight
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        },
        maxAge = 3600          // cache do preflight em segundos
)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public UsuarioController(UsuarioService usuarioService, ObjectMapper objectMapper, EmailService emailService) {
        this.usuarioService = usuarioService;
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    // 1. End-point de login - gera o token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {
        return usuarioService.verificaTokenUsuario(req);
    }

    // 3. CRUD de usuário
    @GetMapping
    public ResponseEntity<List<Usuario>> listAllUsuarios() {
        return ResponseEntity.ok(usuarioService.listAll());
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDto dto) {
        Usuario salvo = usuarioService.criarUsuario(
                dto.getLogin(),
                dto.getPassword(),
                dto.getNivel(),
                dto.getStatus()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(
            @RequestBody UsuarioDto dto,
            @RequestParam String userLogin
    ) throws JsonProcessingException {
        // 1) busca a entidade antiga e converte em DTO
        Usuario antes = usuarioService.findByLogin(dto.getLogin());
        UsuarioDto oldDto = UsuarioDto.fromEntity(antes);

        // 2) cria os JsonNodes
        JsonNode oldNode = objectMapper.valueToTree(oldDto);
        JsonNode newNode = objectMapper.valueToTree(dto);

        // 3) detecta campos alterados
        List<String> camposAlterados = usuarioService.detectarAlteracoes(oldDto, dto);

        // 4) monta strings “campo=valor” para antes e depois
        String oldValues = camposAlterados.stream()
                .map(f -> f + "=" + nodeAsText(oldNode, f))
                .collect(Collectors.joining(", "));
        String newValues = camposAlterados.stream()
                .map(f -> f + "=" + nodeAsText(newNode, f))
                .collect(Collectors.joining(", "));
        String alteracoes = camposAlterados.isEmpty()
                ? "nenhum campo alterado"
                : String.join(", ", camposAlterados);

        // 5) executa atualização
        Usuario atualizado = usuarioService.atualizarUsuario(dto);

        // 6) envia e‑mail informando as mudanças
        emailService.sendSimpleEmail(
                "panicogamer64@gmail.com",
                "Health Safe Farmacia - Usuário Atualizado",
                "Usuário: " + dto.getLogin() + " atualizado por " + userLogin + ".\n" +
                        "Campos alterados: " + alteracoes + "\n" +
                        "Antes: " + oldValues + "\n" +
                        "Depois: " + newValues
        );

        return ResponseEntity.ok(atualizado);
    }

    // método auxiliar para extrair texto de JsonNode a partir do nome do campo
    private String nodeAsText(JsonNode node, String fieldName) {
        JsonNode f = node.get(fieldName);
        return (f == null || f.isNull()) ? "null" : f.asText();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
