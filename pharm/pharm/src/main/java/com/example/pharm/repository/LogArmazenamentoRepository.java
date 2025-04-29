package com.example.pharm.repository;

import com.example.pharm.model.LogArmazenamento;
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
public interface LogArmazenamentoRepository extends JpaRepository<LogArmazenamento, Long> {
    List<LogArmazenamento> findByDescricao(String descricao);

    Page<LogArmazenamento> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    // Busca por status
    List<LogArmazenamento> findByStatusDescricao(String statusDescricao);

    boolean existsByDescricao(String decricao);
}
