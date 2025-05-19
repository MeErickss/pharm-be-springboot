package com.example.pharm.controller;

import com.example.pharm.dto.LoginRequestDto;
import com.example.pharm.dto.UsuarioDto;
import com.example.pharm.model.Usuario;
import com.example.pharm.service.TokenService;
import com.example.pharm.service.UsuarioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {this.usuarioService = usuarioService;}

    // 1. End-point de login - gera o token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {return usuarioService.verificaTokenUsuario(req);}


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

//curl -X POST "http://localhost:8080/api/parametro" -H "Content-Type: application/json" --data "{\"valor\":50,\"vlmin\":10,\"vlmax\":100,\"statusenum\":\"ATIVO\",\"descricao\":\"Parâmetro de teste\",\"unidadeId\":1,\"funcaoenum\":\"PRODUCAO\",\"grandezaId\":2}"
//curl -X DELETE http://localhost:8080/api/parametro/1
//curl -X PUT http://localhost:8080/api/parametro/1 \ -H "Content-Type: application/json" \ -d "{"valor":60,"vlmin":15,"vlmax":110,"statusenum":"INATIVO","descricao":"Atualizado","unidadeId":1,"funcaoenum":"SOMA","grandezaId":2}"

//curl.exe -X POST "http://localhost:8080/api/usuario/login" -H "Content-Type: application/json" -d "{\"login\":\"admin@gmail.com\",\"senha\":\"0000\"}"