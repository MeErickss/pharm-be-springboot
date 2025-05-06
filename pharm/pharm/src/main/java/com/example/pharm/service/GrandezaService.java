package com.example.pharm.service;

import com.example.pharm.model.Grandeza;
import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.GrandezaRepository;
import com.example.pharm.repository.UnidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public void criarGrandeza(String descricao, StatusEnum status) {
        if (grandezaRepository.existsByDescricao(descricao)) {
            throw new RuntimeException("Grandeza já existente!");
        }

        Grandeza g = new Grandeza();
        g.setNome(descricao);
        g.setStatus(status);

        grandezaRepository.save(g);
    }


    public void adicionarUnidades(Long grandezaId, Long unidadeIds) {
        Grandeza g = grandezaRepository.findById(grandezaId).orElseThrow(()->
                new RuntimeException("Grandeza não encontrada!")
        );

        Unidade u = unidadeRepository.findById(unidadeIds).orElseThrow(()->
                new RuntimeException("Unidade não encontrada!")
        );

        g.getUnidades().add(u);
        grandezaRepository.save(g);
    }

    public List<Grandeza> listAll(){
        return grandezaRepository.findAll();
    }

    public Grandeza listId(Long id){
        return grandezaRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }
}
