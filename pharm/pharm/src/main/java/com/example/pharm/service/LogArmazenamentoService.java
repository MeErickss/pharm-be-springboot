package com.example.pharm.service;

import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogArmazenamentoRepository;
import com.example.pharm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class LogArmazenamentoService {
    private final LogArmazenamentoRepository logArmazenamentoRepository;
    private final UsuarioRepository usuarioRepository;

    public LogArmazenamentoService(LogArmazenamentoRepository logArmazenamentoRepository, UsuarioRepository usuarioRepository){
        this.logArmazenamentoRepository = logArmazenamentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public long contarLogArmazenamento() {
        return logArmazenamentoRepository.count();
    }


    public void criarLogAlarmes(Long userId, String descricao, StatusEnum status){

        Usuario user = usuarioRepository.findById(userId).orElseThrow(()->
                new RuntimeException("Usuario não encontrado!")
        );

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());


        LogArmazenamento l = new LogArmazenamento();
        l.setStatus(status);
        l.setDescricao(descricao);
        l.setUser(user);
        l.setDatahora(timeStamp);
        logArmazenamentoRepository.save(l);
    }

    public List<LogArmazenamento> listAll(){
        return logArmazenamentoRepository.findAll();
    }

    public LogArmazenamento listId(Long id){
        return logArmazenamentoRepository.findById(id).orElseThrow(()->
                new RuntimeException("LogArmazenamento não encontrado!")
        );
    }
}
