package com.example.pharm.service;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.dto.ParametroOutDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.FormulaEnum;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ParametroService {

    private final ParametroRepository parametroRepository;
    private final GrandezaRepository grandezaRepository;
    private final UnidadeRepository unidadeRepository;
    private final ObjectMapper objectMapper;

    public ParametroService(ParametroRepository parametroRepository,
                            GrandezaRepository grandezaRepository,
                            UnidadeRepository unidadeRepository,
                            ObjectMapper objectMapper) {
        this.parametroRepository = parametroRepository;
        this.grandezaRepository   = grandezaRepository;
        this.unidadeRepository = unidadeRepository;
        this.objectMapper = objectMapper;
    }

    public long contarParametros() {
        return parametroRepository.count();
    }


    public void criarParametro(String descricao,
                               Integer valor,
                               Integer vlMin,
                               Integer vlMax,
                               StatusEnum status,
                               Long grandezaId,
                               Long unidadeId,
                               FuncaoEnum funcaoEnum,
                               FormulaEnum formulaEnum
    ) {

        Grandeza g = grandezaRepository.findById(grandezaId).orElseThrow(()->
                new RuntimeException("Grandeza não encontrada!")
        );

        Unidade u = unidadeRepository.findById(unidadeId).orElseThrow(()->
                new RuntimeException("Unidade não encontrada!")
        );

        if (parametroRepository.existsByDescricao(descricao)) {
            throw new RuntimeException("Parametro já existente!");
        }
        Parametro p = new Parametro();
        p.setDescricao(descricao);
        p.setGrandeza(g);
        p.setUnidade(u);
        p.setValor(valor);
        p.setVlMin(vlMin);
        p.setVlMax(vlMax);
        p.setFuncao(funcaoEnum);
        p.setFormulaEnum(formulaEnum);
        p.setStatus(status);

        parametroRepository.save(p);
    }


    public Parametro insertParametro(ParametroDto dto) {
        Grandeza grandeza = grandezaRepository
                .findByDescricao(dto.getGrandezaDesc()).orElseThrow(()->
                        new RuntimeException("Parametro não encontrado"));

        Unidade unidade = unidadeRepository
                .findByDescricao(dto.getUnidadeDesc());



        // 2) monta e salva o Parametro
        Parametro p = new Parametro();
        p.setDescricao(dto.getDescricao());
        p.setValor(dto.getValor());
        p.setVlMin(dto.getVlmin());
        p.setVlMax(dto.getVlmax());
        p.setStatus(dto.getStatusenum());
        p.setFuncao(dto.getFuncao());
        p.setGrandeza(grandeza);   // associa a entidade recuperada
        p.setFormulaEnum(dto.getFormulaEnum());
        p.setUnidade(unidade);

        parametroRepository.save(p);
        return p;
    }

    public List<Parametro> listAll(){
        return parametroRepository.findAll();
    }


    public Parametro listId(Long id){
        return parametroRepository.findById(id).orElseThrow(()->
                new RuntimeException("Parametro não encontrado!")
        );
    }

    public Parametro atualizarParametro(ParametroDto parametroDto){
        Parametro p = parametroRepository.findById(parametroDto.getId()).orElseThrow(()->
                new RuntimeException("Parametro não encontrado")
        );

        Grandeza grandeza = grandezaRepository
                .findByDescricao(parametroDto.getGrandezaDesc()).orElseThrow(()->
                        new RuntimeException("Parametro não encontrado"));

        Unidade unidade = unidadeRepository
                .findByDescricao(parametroDto.getUnidadeDesc());

        p.setStatus(parametroDto.getStatusenum());
        p.setFuncao(parametroDto.getFuncao());
        p.setValor(parametroDto.getValor());
        p.setVlMax(parametroDto.getVlmax());
        p.setVlMin(parametroDto.getVlmin());
        p.setDescricao(parametroDto.getDescricao());
        p.setUnidade(unidade);
        p.setGrandeza(grandeza);
        p.setFormulaEnum(parametroDto.getFormulaEnum());
        return parametroRepository.save(p);
    }

    public String deletar(Long id){
        try{
            parametroRepository.deleteById(id);
            return "Parametro deletado com sucesso";
        } catch (Exception error){
            return "Erro ao deletar o parametro";
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
