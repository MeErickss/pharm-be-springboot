package com.example.pharm.service;

import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public long contarUsuarios() {
        return usuariosRepository.count();
    }

    public void criarUsuario(String login, String senha, NivelEnum nivel, StatusEnum status) {
        // 1. Verifica se o login já existe
        if (usuariosRepository.existsByLogin(login)) {
            return; // idempotente
        }


        Usuario u = new Usuario();
        u.setLogin(login);
        u.setPassword(senha);
        u.setNivel(nivel);
        u.setStatus(status);

        usuariosRepository.save(u);
    }

    public List<Usuario> listAll(){
        return usuariosRepository.findAll();
    }

    public Usuario listId(Long id){
        return usuariosRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }
}
