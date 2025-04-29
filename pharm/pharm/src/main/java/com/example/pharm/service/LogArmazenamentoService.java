package com.example.pharm.service;

import com.example.pharm.model.LogAlarmes;
import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.Status;
import com.example.pharm.model.Usuarios;
import com.example.pharm.repository.LogAlarmesRepostiory;
import com.example.pharm.repository.LogArmazenamentoRepository;
import com.example.pharm.repository.StatusRepository;
import com.example.pharm.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
@Transactional
public class LogArmazenamentoService {
    private final StatusRepository statusRepository;
    private final LogArmazenamentoRepository logArmazenamentoRepository;
    private final UsuariosRepository usuariosRepository;

    public LogArmazenamentoService(StatusRepository statusRepository, LogArmazenamentoRepository logArmazenamentoRepository, UsuariosRepository usuariosRepository){
        this.statusRepository = statusRepository;
        this.logArmazenamentoRepository = logArmazenamentoRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public Long contarLogAlarmes(){
        return statusRepository.count();
    }

    public void criarLogAlarmes(Long userId, String descricao, String statusDescricao){
        Status status = statusRepository.findByDescricao(statusDescricao).orElseThrow(()->
                new RuntimeException("Status '" + statusDescricao + "' não encontrado")
        );

        Usuarios user = usuariosRepository.findById(userId).orElseThrow(()->
                new RuntimeException("Usuario '" + userId + "' não encontrado")
        );

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());


        LogArmazenamento l = new LogArmazenamento();
        l.setStatus(status);
        l.setDescricao(descricao);
        l.setUser(user);
        l.setDatahora(timeStamp);
        logArmazenamentoRepository.save(l);
    }
}
