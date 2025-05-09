package com.example.pharm.controller;

import com.example.pharm.dto.LoginRequestDto;
import com.example.pharm.dto.UsuarioDto;
import com.example.pharm.model.Usuario;
import com.example.pharm.service.TokenService;
import com.example.pharm.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {this.usuarioService = usuarioService;}

    // 1. End-point de login - gera o token
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequestDto req) {
        String token = usuarioService.autenticarEGerarToken(req.getLogin(), req.getSenha());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    // 3. CRUD de usuário (se quiser proteger, adicione também o @RequestHeader)
    @GetMapping
    public ResponseEntity<List<Usuario>> listAllUsuarios() {
        List<Usuario> todos = usuarioService.listAll();
        return ResponseEntity.ok(todos);
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

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id,
                                                    @RequestBody UsuarioDto dto) {
        Usuario atualizado = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
