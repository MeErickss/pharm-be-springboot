package com.example.pharm.service;

import com.example.pharm.model.Status;
import com.example.pharm.model.Unidades;
import com.example.pharm.repository.StatusRepository;
import com.example.pharm.repository.UnidadesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UnidadesService {
    private final UnidadesRepository unidadesRepository;
    private final StatusRepository statusRepository;


    public UnidadesService(UnidadesRepository unidadesRepository, StatusRepository statusRepository){
        this.unidadesRepository = unidadesRepository;
        this.statusRepository = statusRepository;
    }

    public Long contarUnidades(){
        return unidadesRepository.count();
    }

    public void criarUnidades(String unidade, String abreviacao, String statusDescricao){
        Status status = statusRepository.findByDescricao(statusDescricao)
                .orElseThrow(() ->
                        new RuntimeException("Status '" + statusDescricao + "' não encontrado")
                );

        Unidades u = new Unidades();
        u.setUnidade(unidade);
        u.setAbreviacao(abreviacao);
        u.setStatus(status);
        unidadesRepository.save(u);
    }

    public List<Unidades> listAll(){
        return unidadesRepository.findAll();
    }

    public Unidades listId(Long id){
        return unidadesRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }
}
