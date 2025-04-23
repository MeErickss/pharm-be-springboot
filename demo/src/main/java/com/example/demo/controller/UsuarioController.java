package com.example.demo.controller;

import com.seuprojeto.model.Usuario;
import com.seuprojeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // POST - CREATE
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return service.salvarUsuario(usuario);
    }

    // GET - READ
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // PUT - UPDATE
    @PutMapping
    public Usuario atualizarUsuario(@RequestBody Usuario usuario) {
        return service.atualizarUsuario(usuario);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        service.deletarUsuario(id);
    }
}