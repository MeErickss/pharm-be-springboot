package com.example.pharm.service;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.FormulaEnum;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final PontoControleRepository pontoControleRepository;
    private final ObjectMapper objectMapper;

    public ParametroService(ParametroRepository parametroRepository,
                            GrandezaRepository grandezaRepository,
                            UnidadeRepository unidadeRepository,
                            ObjectMapper objectMapper,
                            PontoControleRepository pontoControleRepository) {
        this.parametroRepository = parametroRepository;
        this.grandezaRepository   = grandezaRepository;
        this.unidadeRepository = unidadeRepository;
        this.objectMapper = objectMapper;
        this.pontoControleRepository = pontoControleRepository;
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
                               FormulaEnum formulaEnum,
                               Long pontoControleId // pode ser null
    ) {
        Grandeza g = grandezaRepository.findById(grandezaId)
                .orElseThrow(() -> new RuntimeException("Grandeza não encontrada!"));

        Unidade u = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada!"));

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

        if (pontoControleId != null) {
            PontoControle pc = pontoControleRepository.findById(pontoControleId)
                    .orElseThrow(() -> new RuntimeException("PontoControle não encontrado!"));
            p.setPontoControle(pc);
            pc.setStatus(StatusEnum.ATIVO);
        }

        parametroRepository.save(p);
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
        // delega para o outro, passando null
        criarParametro(descricao, valor, vlMin, vlMax, status,
                grandezaId, unidadeId, funcaoEnum, formulaEnum,
                null);
    }

    /**
     * Insere um Parametro a partir de DTO, com pontoControle opcional.
     */
    public Parametro insertParametro(ParametroDto dto) {
        Grandeza grandeza = grandezaRepository
                .findByDescricao(dto.getGrandezaDesc())
                .orElseThrow(() -> new RuntimeException("Grandeza não encontrada"));

        Unidade unidade = unidadeRepository.findByDescricao(dto.getUnidadeDesc());


        Parametro p = new Parametro();
        if(dto.getPontoControle() != null){
            PontoControle pontoControle = pontoControleRepository.findByPontoControle(dto.getPontoControle());
            p.setPontoControle(pontoControle);
            pontoControle.setStatus(StatusEnum.ATIVO);
        }
        p.setDescricao(dto.getDescricao());
        p.setValor(dto.getValor());
        p.setVlMin(dto.getVlmin());
        p.setVlMax(dto.getVlmax());
        p.setStatus(dto.getStatusenum());
        p.setFuncao(dto.getFuncao());
        p.setGrandeza(grandeza);
        p.setFormulaEnum(dto.getFormulaEnum());
        p.setUnidade(unidade);

        return parametroRepository.save(p);
    }

    public List<Parametro> listAll() {
        return parametroRepository.findAll();
    }

    public Parametro listId(Long id) {
        return parametroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parametro não encontrado!"));
    }

    /**
     * Atualiza um Parametro a partir de DTO, com pontoControle opcional.
     */
    public Parametro atualizarParametro(ParametroDto dto) {
        Parametro p = parametroRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Parametro não encontrado"));

        Grandeza grandeza = grandezaRepository
                .findByDescricao(dto.getGrandezaDesc())
                .orElseThrow(() -> new RuntimeException("Grandeza não encontrada"));

        Unidade unidade = unidadeRepository.findByDescricao(dto.getUnidadeDesc());
        // atualiza atributos básicos
        p.setStatus(dto.getStatusenum());
        p.setFuncao(dto.getFuncao());
        p.setValor(dto.getValor());
        p.setVlMax(dto.getVlmax());
        p.setVlMin(dto.getVlmin());
        p.setDescricao(dto.getDescricao());
        p.setUnidade(unidade);
        p.setGrandeza(grandeza);
        p.setFormulaEnum(dto.getFormulaEnum());

        if (dto.getPontoControle() != null) {
            PontoControle novoPc = pontoControleRepository
                    .findByPontoControle(dto.getPontoControle());

            parametroRepository
                    .findByPontoControle(novoPc)
                    .ifPresent(existingParam -> {
                        if (!existingParam.getId().equals(p.getId())) {
                            existingParam.setPontoControle(null);
                            parametroRepository.save(existingParam);
                        }
                    });

            p.setPontoControle(novoPc);
            novoPc.setStatus(StatusEnum.ATIVO);
        } else {
            p.setPontoControle(null);
        }

        return parametroRepository.save(p);
    }


    public String deletar(Long id) {
        try {
            parametroRepository.deleteById(id);
            return "Parametro deletado com sucesso";
        } catch (Exception error) {
            return "Erro ao deletar o parametro";
        }
    }

    public List<String> detectarAlteracoes(Object oldObj, Object newObj) {
        JsonNode oldNode = objectMapper.valueToTree(oldObj);
        JsonNode newNode = objectMapper.valueToTree(newObj);

        List<String> changed = new ArrayList<>();
        Iterator<String> fieldNames = newNode.fieldNames();
        while (fieldNames.hasNext()) {
            String field = fieldNames.next();
            JsonNode v1 = oldNode.get(field);
            JsonNode v2 = newNode.get(field);
            if (v1 == null && v2 != null || v1 != null && !v1.equals(v2)) {
                changed.add(field);
            }
        }
        return changed;
    }
}
