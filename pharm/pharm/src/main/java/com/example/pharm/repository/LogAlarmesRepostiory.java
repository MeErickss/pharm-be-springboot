package com.example.pharm.repository;

import com.example.pharm.model.LogAlarmes;
import com.example.pharm.model.Niveis;
import com.example.pharm.model.Unidades;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogAlarmesRepostiory extends JpaRepository<LogAlarmes, Long> {
    List<LogAlarmes> findByDescricao(String descricao);

    Page<LogAlarmes> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    // Busca por status
    List<LogAlarmes> findByStatusDescricao(String statusDescricao);

    boolean existsByDescricao(String decricao);
}
