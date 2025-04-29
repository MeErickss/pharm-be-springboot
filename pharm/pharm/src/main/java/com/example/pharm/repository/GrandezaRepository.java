package com.example.pharm.repository;

import com.example.pharm.model.Grandeza;
import com.example.pharm.model.LogAlarmes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrandezaRepository extends JpaRepository<Grandeza, Long> {

    List<Grandeza> findByNome(String nome);

    Page<Grandeza> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    // Busca por status
    List<Grandeza> findByStatusDescricao(String statusDescricao);

    boolean existsByNome(String nome);
}
