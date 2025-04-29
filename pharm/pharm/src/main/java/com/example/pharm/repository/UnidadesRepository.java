package com.example.pharm.repository;

import com.example.pharm.model.Unidades;
import com.example.pharm.model.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadesRepository extends JpaRepository<Unidades, Long> {

    // Busca por nome exato do parâmetro (case-sensitive)
    List<Unidades> findByUnidade(String unidade);

    // Busca por nome contendo (case-insensitive) com paginação
    Page<Unidades> findByUnidadeContainingIgnoreCase(String unidade, Pageable pageable);

    // Busca por status
    List<Unidades> findByStatusDescricao(String statusDescricao);


    // Verifica se existe parâmetro com nome específico
    boolean existsByUnidade(String unidade);
}

