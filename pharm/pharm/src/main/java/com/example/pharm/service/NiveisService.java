package com.example.pharm.service;

import com.example.pharm.model.Niveis;
import com.example.pharm.model.Status;
import com.example.pharm.repository.NiveisRepository;
import com.example.pharm.repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NiveisService {
    private final NiveisRepository niveisRepository;
    private final StatusRepository statusRepository;

    public NiveisService(StatusRepository statusRepository, NiveisRepository niveisRepository){
        this.statusRepository = statusRepository;
        this.niveisRepository = niveisRepository;
    }

    public Long contarNiveis(){
        return niveisRepository.count();
    }

    public void criarNiveis(String descricao, String statusDescricao){
        Status status = statusRepository.findByDescricao(statusDescricao).orElseThrow(()->
                new RuntimeException("Status '" + statusDescricao + "' não encontrado")
        );

        Niveis n = new Niveis();
        n.setDescricao(descricao);
        n.setStatus(status);
        niveisRepository.save(n);
    }

}
