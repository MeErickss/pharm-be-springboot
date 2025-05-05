package com.example.pharm.service;

import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogAlarmesRepostiory;
import com.example.pharm.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class LogAlarmesService {
    private final LogAlarmesRepostiory logAlarmesRepostiory;
    private final UsuariosRepository usuariosRepository;

    public LogAlarmesService(LogAlarmesRepostiory logAlarmesRepostiory, UsuariosRepository usuariosRepository){
        this.logAlarmesRepostiory = logAlarmesRepostiory;
        this.usuariosRepository = usuariosRepository;
    }

    public Long contarLogAlarmes(){
        return logAlarmesRepostiory.count();
    }

    public void criarLogAlarmes(Long userId, String descricao, StatusEnum status){

        Usuario user = usuariosRepository.findById(userId).orElseThrow(()->
                    new RuntimeException("Usuario '" + userId + "' não encontrado")
        );

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());


        LogAlarme l = new LogAlarme();
        l.setStatus(status);
        l.setDescricao(descricao);
        l.setUser(user);
        l.setDataHora(timeStamp);
        logAlarmesRepostiory.save(l);
    }

    public List<LogAlarme> listAll(){
        return logAlarmesRepostiory.findAll();
    }

        public LogAlarme listId(Long id){
        return logAlarmesRepostiory.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }
}
