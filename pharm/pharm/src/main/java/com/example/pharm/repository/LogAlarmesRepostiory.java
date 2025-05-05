package com.example.pharm.repository;

import com.example.pharm.model.LogAlarme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogAlarmesRepostiory extends JpaRepository<LogAlarme, Long> {

    Page<LogAlarme> findByDescricao(String descricao, Pageable pageable);

    // Busca por status
    List<LogAlarme> findByStatusDescricao(String statusDescricao);

    boolean existsByDescricao(String decricao);
}
