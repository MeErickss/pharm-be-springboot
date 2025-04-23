package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // CREATE (INSERT)
    public Usuario salvarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    // READ (SELECT)
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // UPDATE
    public Usuario atualizarUsuario(Usuario usuario) {
        if (repository.existsById(usuario.getId())) {
            return repository.save(usuario);
        }
        return null;
    }

    // DELETE
    public void deletarUsuario(Long id) {
        repository.deleteById(id);
    }
}