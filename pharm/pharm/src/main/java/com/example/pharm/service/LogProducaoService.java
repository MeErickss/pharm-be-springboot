package com.example.pharm.service;

import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Status;
import com.example.pharm.model.Usuarios;
import com.example.pharm.repository.LogProducaoRepository;
import com.example.pharm.repository.StatusRepository;
import com.example.pharm.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
@Transactional
public class LogProducaoService {
    private final StatusRepository statusRepository;
    private final LogProducaoRepository logProducaoRepository;
    private final UsuariosRepository usuariosRepository;

    public LogProducaoService(StatusRepository statusRepository, LogProducaoRepository logProducaoRepository, UsuariosRepository usuariosRepository){
        this.statusRepository = statusRepository;
        this.logProducaoRepository = logProducaoRepository;
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


        LogProducao l = new LogProducao();
        l.setStatus(status);
        l.setDescricao(descricao);
        l.setUser(user);
        l.setDataHora(timeStamp);
        logProducaoRepository.save(l);
    }
}
