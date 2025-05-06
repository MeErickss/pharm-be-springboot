package com.example.pharm.service;

import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.UnidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;


    public UnidadeService(UnidadeRepository unidadeRepository){
        this.unidadeRepository = unidadeRepository;
    }

    public Long contarUnidades(){
        return unidadeRepository.count();
    }

    public void criarUnidades(String descricao, String abreviacao, StatusEnum statusEnum){
        if(unidadeRepository.existsByDescricao(descricao)){
            throw new RuntimeException("Unidade já existente!");
        }

        Unidade u = new Unidade();
        u.setUnidade(descricao);
        u.setAbreviacao(abreviacao);
        u.setStatus(statusEnum);
        unidadeRepository.save(u);
    }

    public List<Unidade> listAll(){
        return unidadeRepository.findAll();
    }

    public Unidade listId(Long id){
        return unidadeRepository.findById(id).orElseThrow(()->
                new RuntimeException("Unidade não encontrada!")
        );
    }
}
