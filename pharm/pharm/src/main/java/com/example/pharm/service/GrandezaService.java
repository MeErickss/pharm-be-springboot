package com.example.pharm.service;

import com.example.pharm.dto.GrandezaDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.GrandezaRepository;
import com.example.pharm.repository.UnidadeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GrandezaService {

    private final GrandezaRepository grandezaRepository;
    private ObjectMapper objectMapper;

    public GrandezaService(GrandezaRepository grandezaRepository,
                           ObjectMapper objectMapper) {
        this.grandezaRepository = grandezaRepository;
        this.objectMapper = objectMapper;
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
