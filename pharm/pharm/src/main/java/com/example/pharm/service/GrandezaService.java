package com.example.pharm.service;

import com.example.pharm.dto.GrandezaDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.GrandezaRepository;
import com.example.pharm.repository.UnidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GrandezaService {

    private final GrandezaRepository grandezaRepository;
    private final UnidadeRepository unidadeRepository;

    public GrandezaService(GrandezaRepository grandezaRepository,
                           UnidadeRepository unidadeRepository) {
        this.grandezaRepository = grandezaRepository;
        this.unidadeRepository = unidadeRepository;
    }

    public long contarGrandeza() {
        return grandezaRepository.count();
    }


    public Grandeza criarGrandeza(String descricao, StatusEnum status) {
        if (grandezaRepository.existsByDescricao(descricao)) {
            throw new RuntimeException("Grandeza já existente!");
        }

        Grandeza g = new Grandeza();
        g.setDescricao(descricao);
        g.setStatus(status);

        grandezaRepository.save(g);
        return g;
    }


    public List<Grandeza> listAll(){
        return grandezaRepository.findAll();
    }

    public Grandeza listId(Long id){
        return grandezaRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }

    public Grandeza atualizarGrandeza(GrandezaDto grandezaDto){
        Grandeza g = grandezaRepository.findById(grandezaDto.getId()).orElseThrow(()->
                new RuntimeException("Grandeza não encontrado")
        );
        return grandezaRepository.save(g);
    }

    public String deletar(Long id){
        try{
            grandezaRepository.deleteById(id);
            return "Grandeza deletada com sucesso";
        } catch (Exception error){
            return "Erro ao deletar a Grandeza";
        }
    }
}
