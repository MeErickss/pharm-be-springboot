package com.example.pharm.service;

import com.example.pharm.dto.LoginRequestDto;
import com.example.pharm.dto.UsuarioDto;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, TokenService tokenService, PasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    public long contarUsuarios() {
        return usuarioRepository.count();
    }

    public Usuario findByLogin(String login) {
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario criarUsuario(String login, String senha, NivelEnum nivel, StatusEnum status) {
        if (usuarioRepository.existsByLogin(login)) {
            throw new RuntimeException("Login de usuário já existente!");
        }

        Usuario u = new Usuario();
        u.setLogin(login);
        u.setPassword(passwordEncoder.encode(senha));
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
        Usuario u = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Login ou senha inválidos"));

        if (!passwordEncoder.matches(senha, u.getPassword())) {
            throw new RuntimeException("Login ou senha inválidos");
        }

        if (u.getStatus() != StatusEnum.ATIVO) {
            throw new RuntimeException("Usuário inativo");
        }

        return tokenService.criarTokenParaUsuario(u.getId());
    }


    public ResponseEntity<Map<String, String>> verificaTokenUsuario(LoginRequestDto req){
        String token = autenticarEGerarToken(req.getLogin(), req.getSenha());
        Usuario usuario = findByLogin(req.getLogin());

        ResponseCookie cookie = ResponseCookie.from("JWT", token)
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofHours(2))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("nivel", usuario.getNivel().toString(), "cookie", cookie.toString()));
    }

    public String nodeAsText(JsonNode node, String fieldName) {
        JsonNode f = node.get(fieldName);
        return (f == null || f.isNull()) ? "null" : f.asText();
    }

    public List<String> detectarAlteracoes(Object oldObj, Object newObj) {
        JsonNode oldNode = objectMapper.valueToTree(oldObj);
        JsonNode newNode = objectMapper.valueToTree(newObj);
        System.out.print("oldNode");
        System.out.print(oldNode);
        System.out.print("newObj");
        System.out.print(newObj);


        List<String> changed = new ArrayList<>();
        Iterator<String> fieldNames = newNode.fieldNames();
        while (fieldNames.hasNext()) {
            String field = fieldNames.next();
            JsonNode v1 = oldNode.get(field);
            JsonNode v2 = newNode.get(field);
            if (v1 == null && v2 != null
                    || v1 != null && !v1.equals(v2)) {
                changed.add(field);
            }
        }
        return changed;
    }
}
