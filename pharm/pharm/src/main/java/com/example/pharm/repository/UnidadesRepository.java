package com.example.pharm.repository;

import com.example.pharm.model.Unidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadesRepository extends JpaRepository<Unidade, Long> {

    // Busca por nome exato do parâmetro (case-sensitive)
    Page<Unidade> findByUnidade(String unidade, Pageable pageable);

    // Busca por status
    List<Unidade> findByStatusDescricao(String statusDescricao);


    // Verifica se existe parâmetro com nome específico
    boolean existsByUnidade(String unidade);
}

