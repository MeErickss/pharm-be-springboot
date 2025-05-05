package com.example.pharm.service;

import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.UnidadesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UnidadesService {
    private final UnidadesRepository unidadesRepository;


    public UnidadesService(UnidadesRepository unidadesRepository){
        this.unidadesRepository = unidadesRepository;
    }

    public Long contarUnidades(){
        return unidadesRepository.count();
    }

    public void criarUnidades(String unidade, String abreviacao, StatusEnum statusEnum){
        Unidade u = new Unidade();
        u.setUnidade(unidade);
        u.setAbreviacao(abreviacao);
        u.setStatus(statusEnum);
        unidadesRepository.save(u);
    }

    public List<Unidade> listAll(){
        return unidadesRepository.findAll();
    }

    public Unidade listId(Long id){
        return unidadesRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }
}
