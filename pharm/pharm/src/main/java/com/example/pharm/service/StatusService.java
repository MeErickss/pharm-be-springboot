package com.example.pharm.service;

import com.example.pharm.model.Status;
import com.example.pharm.repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

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

    public List<Status> listAll(){
        return statusRepository.findAll();
    }

    public Status listId(Long id){
        return statusRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }

}
