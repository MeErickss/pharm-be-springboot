package com.example.pharm.service;

import com.example.pharm.model.Status;
import com.example.pharm.repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public void criarStatus(String descricao) {
        if (statusRepository.existsByDescricao(descricao)) {
            return;  // torna idempotente
        }
        Status s = new Status();
        s.setDescricao(descricao);
        statusRepository.save(s);
    }

    public long contarStatus() {
        return statusRepository.count();
    }
}
