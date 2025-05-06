package com.example.pharm.repository;

import com.example.pharm.model.Unidade;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    Page<Unidade> findByDescricao(String descricao, Pageable pageable);

    List<Unidade> findByStatus(StatusEnum statusEnum);

    boolean existsByDescricao(String decricao);
}

