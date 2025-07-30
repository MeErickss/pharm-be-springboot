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
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        },
        maxAge = 3600
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {
        return usuarioService.verificaTokenUsuario(req);
    }
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
        Usuario antes = usuarioService.findByLogin(dto.getLogin());
        UsuarioDto oldDto = UsuarioDto.fromEntity(antes);

        JsonNode oldNode = objectMapper.valueToTree(oldDto);
        JsonNode newNode = objectMapper.valueToTree(dto);

        List<String> camposAlterados = usuarioService.detectarAlteracoes(oldDto, dto);

        String oldValues = camposAlterados.stream()
                .map(f -> f + "=" + usuarioService.nodeAsText(oldNode, f))
                .collect(Collectors.joining(", "));
        String newValues = camposAlterados.stream()
                .map(f -> f + "=" + usuarioService.nodeAsText(newNode, f))
                .collect(Collectors.joining(", "));
        String alteracoes = camposAlterados.isEmpty()
                ? "nenhum campo alterado"
                : String.join(", ", camposAlterados);

        Usuario atualizado = usuarioService.atualizarUsuario(dto);

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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
