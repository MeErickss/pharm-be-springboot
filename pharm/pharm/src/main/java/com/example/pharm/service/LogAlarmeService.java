package com.example.pharm.service;

import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogAlarmeRepostiory;
import com.example.pharm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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


    public void criarLogAlarmes(Long userId, String descricao, StatusEnum status){

        Usuario u = usuarioRepository.findById(userId).orElseThrow(()->
                new RuntimeException("Usuario não encontrada!")
        );;

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());


        LogAlarme l = new LogAlarme();
        l.setStatus(status);
        l.setDescricao(descricao);
        l.setUser(u);
        l.setDataHora(timeStamp);
        logAlarmeRepostiory.save(l);
    }

    public List<LogAlarme> listAll(){
        return logAlarmeRepostiory.findAll();
    }

    public LogAlarme listId(Long id){
        return logAlarmeRepostiory.findById(id).orElseThrow(()->
                new RuntimeException("Grandeza não encontrada!")
        );
    }
}
