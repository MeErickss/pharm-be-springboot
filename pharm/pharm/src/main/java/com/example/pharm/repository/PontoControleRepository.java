package com.example.pharm.repository;


import com.example.pharm.model.PontoControle;
import com.example.pharm.model.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PontoControleRepository extends JpaRepository<PontoControle, Long> {

    PontoControle findByPontoControle(String pontoControle);

    List<PontoControle> findByStatus(StatusEnum statusEnum);

    boolean existsByPontoControle(String pontoControle);
}

