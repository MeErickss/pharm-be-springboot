package com.example.pharm.service;

import com.example.pharm.dto.UnidadeDto;
import com.example.pharm.model.Grandeza;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.GrandezaRepository;
import com.example.pharm.repository.UnidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;
    private final GrandezaRepository grandezaRepository;


    public UnidadeService(UnidadeRepository unidadeRepository, GrandezaRepository grandezaRepository){
        this.unidadeRepository = unidadeRepository;
        this.grandezaRepository = grandezaRepository;
    }

    public Long contarUnidades(){
        return unidadeRepository.count();
    }

    public Unidade criarUnidades(String descricao, String abreviacao, StatusEnum statusEnum, Long grandezaId){
        if(unidadeRepository.existsByDescricao(descricao)){
            throw new RuntimeException("Unidade já existente!");
        }

        Grandeza g = grandezaRepository.findById(grandezaId).orElseThrow(()->
                new RuntimeException("Unidade não encontrada!")
        );

        Unidade u = new Unidade();
        u.setUnidade(descricao);
        u.setAbreviacao(abreviacao);
        u.setStatus(statusEnum);
        u.setGrandeza(g);
        unidadeRepository.save(u);
        return u;
    }

    public void insertUnidades(UnidadeDto unidadeDto){


        Grandeza g = grandezaRepository.findByDescricao(unidadeDto.getGrandeza()).orElseThrow(()->
                new RuntimeException("Grandeza não encontrada"));

        Unidade u = new Unidade();
        u.setUnidade(unidadeDto.getUnidade());
        u.setAbreviacao(unidadeDto.getAbreviacao());
        u.setStatus(unidadeDto.getStatus());
        u.setGrandeza(g);
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

    public Unidade atualizarUnidade(UnidadeDto unidadeDto){
        Unidade u = unidadeRepository.findById(unidadeDto.getId()).orElseThrow(()->
                new RuntimeException("Unidade não encontrada!")
        );

        Grandeza g = grandezaRepository.findByDescricao(unidadeDto.getGrandeza()).orElseThrow(()->
                new RuntimeException("Grandeza não encontrada!")
        );

        u.setUnidade(unidadeDto.getUnidade());
        u.setAbreviacao(unidadeDto.getAbreviacao());
        u.setUnidade(unidadeDto.getUnidade());
        u.setStatus(unidadeDto.getStatus());
        u.setGrandeza(g);
        return unidadeRepository.save(u);
    }

    public String deletar(Long id){
        try{
            unidadeRepository.deleteById(id);
            return "Unidade deletada com sucesso";
        } catch (Exception error){
            return "Erro ao deletar a Unidade";
        }
    }
}
