package com.example.pharm.service;

import com.example.pharm.dto.UsuarioDto;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    public UsuarioService(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
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
        return u;
    }

    public List<Usuario> listAll(){
        return usuarioRepository.findAll();
    }

    public Usuario listId(Long id){
        return usuarioRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }

    public Usuario atualizar(Long id, UsuarioDto usuarioDto){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->
                new RuntimeException("Usuario não encontrado")
        );
        return usuarioRepository.save(u);
    }

    public String deletar(Long id){
        try{
            usuarioRepository.deleteById(id);
            return "Usuario deletado com sucesso";
        } catch (Exception error){
            return "Erro ao deletar o Usuario";
        }
    }

    public String autenticarEGerarToken(String login, String senha) {
        // usa sua query customizada
        Usuario u = usuarioRepository.findByLoginAndSenha(login, senha);

        // se não encontrar, retorna null → tratamos como credenciais inválidas
        if (u == null) {
            throw new RuntimeException("Login ou senha inválidos");
        }
        // checa status
        if (u.getStatus() != StatusEnum.ATIVO) {
            throw new RuntimeException("Usuário inativo");
        }
        // gera e retorna token
        return tokenService.criarTokenParaUsuario(u.getId());
    }
}
