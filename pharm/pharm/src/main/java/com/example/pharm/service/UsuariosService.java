package com.example.pharm.service;

import com.example.pharm.model.Niveis;
import com.example.pharm.model.Status;
import com.example.pharm.model.Usuarios;
import com.example.pharm.repository.NiveisRepository;
import com.example.pharm.repository.StatusRepository;
import com.example.pharm.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final StatusRepository statusRepository;
    private final NiveisRepository niveisRepository;

    public UsuariosService(UsuariosRepository usuariosRepository,
                           StatusRepository statusRepository,
                           NiveisRepository niveisRepository) {
        this.usuariosRepository = usuariosRepository;
        this.statusRepository = statusRepository;
        this.niveisRepository = niveisRepository;
    }

    public long contarUsuarios() {
        return usuariosRepository.count();
    }

    public void criarUsuario(String login, String senha, Long nivelId, String statusDescricao) {
        // 1. Verifica se o login já existe
        if (usuariosRepository.existsByLogin(login)) {
            return; // idempotente
        }

        // 2. Busca o Status correto
        Status status = statusRepository.findByDescricao(statusDescricao)
                .orElseThrow(() ->
                        new RuntimeException("Status '" + statusDescricao + "' não encontrado")
                );

        Niveis nivel = niveisRepository.findById(nivelId).orElseThrow(()->
                    new RuntimeException("Nivel de acesso:  '" + nivelId + "' não encontrado")
                );

        // 3. Cria e persiste sem ID manual
        Usuarios u = new Usuarios();
        u.setLogin(login);
        u.setPassword(senha);
        u.setNivel(nivel);
        u.setStatus(status);

        usuariosRepository.save(u);
    }
}
