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

    /**
     * Cria um novo Parametro, com pontoControle opcional.
     */
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

        // seta PontoControle somente se fornecido
        if (pontoControleId != null) {
            PontoControle pc = pontoControleRepository.findById(pontoControleId)
                    .orElseThrow(() -> new RuntimeException("PontoControle não encontrado!"));
            p.setPontoControle(pc);
        }

        parametroRepository.save(p);
    }

    /**
     * Insere um Parametro a partir de DTO, com pontoControle opcional.
     */
    public Parametro insertParametro(ParametroDto dto) {
        Grandeza grandeza = grandezaRepository
                .findByDescricao(dto.getGrandezaDesc())
                .orElseThrow(() -> new RuntimeException("Grandeza não encontrada"));

        // Unidade opcional
        Unidade unidade = null;
        if (dto.getUnidadeDesc() != null) {
            unidade = unidadeRepository.findByDescricao(dto.getUnidadeDesc());
        }

        Parametro p = new Parametro();
        p.setDescricao(dto.getDescricao());
        p.setValor(dto.getValor());
        p.setVlMin(dto.getVlmin());
        p.setVlMax(dto.getVlmax());
        p.setStatus(dto.getStatusenum());
        p.setFuncao(dto.getFuncao());
        p.setGrandeza(grandeza);
        p.setFormulaEnum(dto.getFormulaEnum());
        p.setUnidade(unidade);

        // PontoControle opcional
        if (dto.getPontoControle() != null) {
            PontoControle pc = pontoControleRepository.findByPontoControle(dto.getPontoControle());
            p.setPontoControle(pc);
        }

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

        // Unidade opcional
        Unidade unidade = null;
        if (dto.getUnidadeDesc() != null) {
            unidade = unidadeRepository.findByDescricao(dto.getUnidadeDesc());
        }

        p.setStatus(dto.getStatusenum());
        p.setFuncao(dto.getFuncao());
        p.setValor(dto.getValor());
        p.setVlMax(dto.getVlmax());
        p.setVlMin(dto.getVlmin());
        p.setDescricao(dto.getDescricao());
        p.setUnidade(unidade);
        p.setGrandeza(grandeza);
        p.setFormulaEnum(dto.getFormulaEnum());

        // atualização de PontoControle opcional
        if (dto.getPontoControle() != null) {
            PontoControle pc = pontoControleRepository.findByPontoControle(dto.getPontoControle());
            p.setPontoControle(pc);
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
