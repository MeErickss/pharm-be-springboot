package com.example.pharm.repository;

import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.LogProducao;
import com.example.pharm.model.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogProducaoRepository extends JpaRepository<LogProducao, Long> {

    Page<LogProducao> findByDescricao(String descricao, Pageable pageable);

    List<LogProducao> findByStatus(StatusEnum statusEnum);

    boolean existsByDescricao(String decricao);
}
