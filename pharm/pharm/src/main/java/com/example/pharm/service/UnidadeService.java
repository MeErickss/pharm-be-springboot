package com.example.pharm.service;

import com.example.pharm.dto.UnidadeDto;
import com.example.pharm.model.Grandeza;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.GrandezaRepository;
import com.example.pharm.repository.UnidadeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;
    private final GrandezaRepository grandezaRepository;
    private final ObjectMapper objectMapper;


    public UnidadeService(UnidadeRepository unidadeRepository, GrandezaRepository grandezaRepository, ObjectMapper objectMapper){
        this.unidadeRepository = unidadeRepository;
        this.grandezaRepository = grandezaRepository;
        this.objectMapper = objectMapper;
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
                new RuntimeException("Unidade não encontrado")
        );

        u.setAbreviacao(unidadeDto.getAbreviacao());
        u.setUnidade(unidadeDto.getUnidade());
        u.setStatus(unidadeDto.getStatus());
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

    public String nodeAsText(JsonNode node, String fieldName) {
        JsonNode f = node.get(fieldName);
        return (f == null || f.isNull()) ? "null" : f.asText();
    }

    public List<String> detectarAlteracoes(Object oldObj, Object newObj) {
        JsonNode oldNode = objectMapper.valueToTree(oldObj);
        JsonNode newNode = objectMapper.valueToTree(newObj);
        System.out.print("oldNode");
        System.out.print(oldNode);
        System.out.print("newObj");
        System.out.print(newObj);


        List<String> changed = new ArrayList<>();
        Iterator<String> fieldNames = newNode.fieldNames();
        while (fieldNames.hasNext()) {
            String field = fieldNames.next();
            JsonNode v1 = oldNode.get(field);
            JsonNode v2 = newNode.get(field);
            if (v1 == null && v2 != null
                    || v1 != null && !v1.equals(v2)) {
                changed.add(field);
            }
        }
        return changed;
    }
}
