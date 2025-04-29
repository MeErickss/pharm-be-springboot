package com.example.pharm.service;

import com.example.pharm.model.*;
import com.example.pharm.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ParametrosService {

    private final ParametrosRepository parametrosRepository;
    private final StatusRepository statusRepository;
    private final FuncoesRepository funcoesRepository;
    private final GrandezaRepository grandezaRepository;
    private final UnidadesRepository unidadesRepository;

    public ParametrosService(ParametrosRepository parametrosRepository,
                             StatusRepository statusRepository,
                             FuncoesRepository funcoesRepository,
                             GrandezaRepository grandezaRepository,
                             UnidadesRepository unidadesRepository) {
        this.parametrosRepository = parametrosRepository;
        this.statusRepository     = statusRepository;
        this.funcoesRepository    = funcoesRepository;
        this.grandezaRepository   = grandezaRepository;
        this.unidadesRepository   = unidadesRepository;
    }

    public long contarParametros() {
        return parametrosRepository.count();
    }

    public void criarParametro(String parametro,
                               Integer valor,
                               Integer vlMin,
                               Integer vlMax,
                               String statusDescricao
    ) {

        if (parametrosRepository.existsByParametro(parametro)) {
            return; // já existe, nada a fazer
        }

        Status status = statusRepository.findByDescricao(statusDescricao)
                .orElseThrow(() -> new RuntimeException("Status '" + statusDescricao + "' não encontrado"));

        Parametros p = new Parametros();
        p.setParametro(parametro);
        p.setValor(valor);
        p.setVlMin(vlMin);
        p.setVlMax(vlMax);
        p.setStatus(status);

        parametrosRepository.save(p);
    }

    /** Se precisar alterar a única função associada **/
    public void setFuncao(Long parametroId, Long funcaoId) {
        Parametros p = parametrosRepository.findById(parametroId)
                .orElseThrow(() -> new RuntimeException("Parâmetro não encontrado: " + parametroId));

        Funcoes f = funcoesRepository.findById(funcaoId)
                .orElseThrow(() -> new RuntimeException("Função não encontrada: " + funcaoId));

        if (p.getFuncoes() != null){
            p.getFuncoes().clear();
        }
        p.getFuncoes().add(f);
        parametrosRepository.save(p);
    }

    /** Mesma lógica para grandeza **/
    public void setGrandeza(Long parametroId, Long grandezaId) {
        Parametros p = parametrosRepository.findById(parametroId)
                .orElseThrow(() -> new RuntimeException("Parâmetro não encontrado: " + parametroId));

        Grandeza g = grandezaRepository.findById(grandezaId)
                .orElseThrow(() -> new RuntimeException("Grandeza não encontrada: " + grandezaId));

        if (p.getGrandeza() != null){
            p.getGrandeza().clear();
        }
        p.getGrandeza().add(g);
        parametrosRepository.save(p);
    }

    /** E para unidade **/
    public void setUnidade(Long parametroId, Long unidadeId) {
        Parametros p = parametrosRepository.findById(parametroId)
                .orElseThrow(() -> new RuntimeException("Parâmetro não encontrado: " + parametroId));

        Unidades u = unidadesRepository.findById(unidadeId)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada: " + unidadeId));

        if (p.getUnidades() != null){
            p.getUnidades().clear();
        }
        p.getUnidades().add(u);
        parametrosRepository.save(p);
    }
}
