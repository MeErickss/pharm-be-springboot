package com.example.pharm.repository;

import com.example.pharm.model.Funcoes;
import com.example.pharm.model.Grandeza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncoesRepository extends JpaRepository<Funcoes, Long> {
    List<Funcoes> findByNome(String nome);

    Page<Funcoes> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    // Busca por status
    List<Funcoes> findByStatusDescricao(String statusDescricao);

    boolean existsByNome(String nome);
}
