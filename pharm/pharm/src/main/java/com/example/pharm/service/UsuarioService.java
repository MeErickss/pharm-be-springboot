package com.example.pharm.service;

import com.example.pharm.dto.LoginRequestDto;
import com.example.pharm.dto.UsuarioDto;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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

    public Usuario findByLogin(String login) {
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
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

    public Usuario atualizarUsuario(UsuarioDto usuarioDto){
        Usuario u = usuarioRepository.findById(usuarioDto.getId()).orElseThrow(()->
                new RuntimeException("Usuario não encontrado")
        );

        u.setLogin(usuarioDto.getLogin());
        u.setStatus(usuarioDto.getStatus());
        u.setPassword(usuarioDto.getPassword());
        u.setNivel(usuarioDto.getNivel());

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

    public ResponseEntity<Map<String, String>> verificaTokenUsuario(LoginRequestDto req){
        String token = autenticarEGerarToken(req.getLogin(), req.getSenha());
        Usuario usuario = findByLogin(req.getLogin()); // Novo método para buscar usuário

        ResponseCookie cookie = ResponseCookie.from("JWT", token)
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofHours(2))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("nivel", usuario.getNivel().toString(), "cookie", cookie.toString()));
    }
}
