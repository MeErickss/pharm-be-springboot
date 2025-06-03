package com.example.pharm.service;

import com.example.pharm.dto.LogAlarmeDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogAlarmeRepostiory;
import com.example.pharm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
public class LogAlarmeService {
    private final LogAlarmeRepostiory logAlarmeRepostiory;
    private final UsuarioRepository usuarioRepository;

    public LogAlarmeService(LogAlarmeRepostiory logAlarmeRepostiory, UsuarioRepository usuarioRepository){
        this.logAlarmeRepostiory = logAlarmeRepostiory;
        this.usuarioRepository = usuarioRepository;
    }

    public long contarLogAlarme() {
        return logAlarmeRepostiory.count();
    }


    public void criarLogAlarmes(LogAlarmeDto dto){

        Usuario u = usuarioRepository.findByLogin(dto.getUser()).orElseThrow(()->
                new RuntimeException("Usuario não encontrada!")
        );

        Instant time = Instant.now();

        LogAlarme l = new LogAlarme();
        l.setStatus(dto.getStatus());
        l.setDescricao(dto.getDescricao());
        l.setUser(u);
        l.setDataHora(String.valueOf(time));
        logAlarmeRepostiory.save(l);
    }

    public void insertLogAlarmes(LogAlarmeDto dto){

        Usuario u = usuarioRepository.findByLogin(dto.getUser()).orElseThrow(()->
                new RuntimeException("Usuario não encontrado!"+dto.getDescricao() +""+ dto.getUser())
        );

        Instant time = Instant.now();

        LogAlarme l = new LogAlarme();
        l.setStatus(StatusEnum.ATIVO);
        l.setDescricao(dto.getDescricao());
        l.setUser(u);
        l.setDataHora(String.valueOf(time));
        logAlarmeRepostiory.save(l);
    }

    public Page<LogAlarme> listAll(Pageable pageable) {
        return logAlarmeRepostiory.findAll(pageable);
    }

    public LogAlarme listId(Long id){
        return logAlarmeRepostiory.findById(id).orElseThrow(()->
                new RuntimeException("Grandeza não encontrada!")
        );
    }

    public LogAlarme atualizar(Long id, LogAlarmeDto logAlarmeDto){
        LogAlarme l = logAlarmeRepostiory.findById(id).orElseThrow(()->
                new RuntimeException("LogAlarmes não encontrado")
        );
        return logAlarmeRepostiory.save(l);
    }

    public String deletar(Long id){
        try{
            logAlarmeRepostiory.deleteById(id);
            return "LogAlarme deletado com sucesso";
        } catch (Exception error){
            return "Erro ao deletar o LogAlarme";
        }
    }
}
