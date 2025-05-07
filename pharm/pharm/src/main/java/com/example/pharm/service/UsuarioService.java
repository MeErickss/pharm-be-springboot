package com.example.pharm.service;

import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public long contarUsuarios() {
        return usuarioRepository.count();
    }

    public Usuario criarUsuario(String login, String senha, NivelEnum nivel, StatusEnum status) {
        // 1. Verifica se o login já existe
        if (usuarioRepository.existsByLogin(login)) {
            throw new RuntimeException("Login de usuário já existente!");
        }


        Usuario u = new Usuario();
        u.setLogin(login);
        u.setPassword(senha);
        u.setNivel(nivel);
        u.setStatus(status);

        usuarioRepository.save(u);
    }

    public List<Usuario> listAll(){
        return usuarioRepository.findAll();
    }

    public Usuario listId(Long id){
        return usuarioRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }
}
