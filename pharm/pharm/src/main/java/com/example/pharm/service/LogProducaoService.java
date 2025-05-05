package com.example.pharm.service;

import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Status;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogProducaoRepository;
import com.example.pharm.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class LogProducaoService {
    private final LogProducaoRepository logProducaoRepository;
    private final UsuariosRepository usuariosRepository;

    public LogProducaoService(LogProducaoRepository logProducaoRepository, UsuariosRepository usuariosRepository){
        this.logProducaoRepository = logProducaoRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public Long contarLogAlarmes(){
        return logProducaoRepository.count();
    }

    public void criarLogAlarmes(Long userId, String descricao, StatusEnum status){

        Usuario user = usuariosRepository.findById(userId).orElseThrow(()->
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

    public List<LogProducao> listAll(){
        return logProducaoRepository.findAll();
    }

    public LogProducao listId(Long id){
        return logProducaoRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }
}
