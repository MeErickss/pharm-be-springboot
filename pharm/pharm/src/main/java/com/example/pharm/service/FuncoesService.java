package com.example.pharm.service;

import com.example.pharm.model.Funcoes;
import com.example.pharm.model.Status;
import com.example.pharm.repository.FuncoesRepository;
import com.example.pharm.repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FuncoesService {
    private final FuncoesRepository funcoesRepository;
    private final StatusRepository statusRepository;

    public FuncoesService(FuncoesRepository funcoesRepository,
                          StatusRepository statusRepository) {
        this.funcoesRepository = funcoesRepository;
        this.statusRepository = statusRepository;
    }

    public long contarFuncoes() {
        return funcoesRepository.count();
    }

    public void criarFuncao(String nome, String statusDescricao) {
        if (funcoesRepository.existsByNome(nome)) {
            return;  // idempotente
        }
        Status status = statusRepository.findByDescricao(statusDescricao)
                .orElseThrow(() ->
                        new RuntimeException("Status '" + statusDescricao + "' não encontrado")
                );

        Funcoes f = new Funcoes();
        f.setNome(nome);
        f.setStatus(status);
        funcoesRepository.save(f);  // agora faz INSERT puro e preenche status_descricao com "ATIVO"
    }
}
