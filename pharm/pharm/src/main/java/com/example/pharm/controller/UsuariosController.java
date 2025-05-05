package com.example.pharm.controller;

import com.example.pharm.model.Usuario;
import com.example.pharm.service.UsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService){
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listAll(){
        List<Usuario> todos = usuariosService.listAll();
        return ResponseEntity.ok(todos);
    }

}

