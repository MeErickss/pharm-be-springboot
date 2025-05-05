package com.example.pharm.repository;

import com.example.pharm.model.LogArmazenamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogArmazenamentoRepository extends JpaRepository<LogArmazenamento, Long> {

    Page<LogArmazenamento> findByDescricao(String descricao, Pageable pageable);

    // Busca por status
    List<LogArmazenamento> findByStatusDescricao(String statusDescricao);

    boolean existsByDescricao(String decricao);
}
