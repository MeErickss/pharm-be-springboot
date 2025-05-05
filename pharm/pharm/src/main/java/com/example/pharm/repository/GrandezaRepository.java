package com.example.pharm.repository;

import com.example.pharm.model.Grandeza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrandezaRepository extends JpaRepository<Grandeza, Long> {

    Page<Grandeza> findByNome(String nome, Pageable pageable);

    // Busca por status
    List<Grandeza> findByStatusDescricao(Enum statusDescricao);

    boolean existsByNome(String nome);
}
