package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.dto.UsuarioDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Usuario;
import com.example.pharm.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listAll(){
        List<Usuario> todos = usuarioService.listAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarParametro(@RequestBody UsuarioDto dto) {
        Usuario salvo = usuarioService.criarUsuario(
                dto.getLogin(),
                dto.getPassword(),
                dto.getNivel(),
                dto.getStatus()
        );
        return ResponseEntity.ok(salvo);
    }

}

