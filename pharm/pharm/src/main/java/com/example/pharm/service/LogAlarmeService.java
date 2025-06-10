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

import java.sql.Timestamp;
import java.util.List;

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


    public LogAlarme criarLogAlarmes(Long userId, String descricao, StatusEnum status, String dataHora){

        Usuario u = usuarioRepository.findById(userId).orElseThrow(()->
                new RuntimeException("Usuario não encontrado!")
        );


        LogAlarme l = new LogAlarme();
        l.setStatus(status);
        l.setDescricao(descricao);
        l.setUser(u);
        l.setDataHora(dataHora);
        logAlarmeRepostiory.save(l);

        return l;
    }

    public void insertLogAlarmes(LogAlarmeDto logAlarmeDto){
        Usuario u = usuarioRepository.findByLogin(logAlarmeDto.getUser()).orElseThrow(()->
                new RuntimeException("Usuario não encontrado!")
        );

        Timestamp time = new Timestamp(System.currentTimeMillis());

        LogAlarme l = new LogAlarme();
        l.setUser(u);
        l.setStatus(StatusEnum.ATIVO);
        l.setDescricao(logAlarmeDto.getDescricao());
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
