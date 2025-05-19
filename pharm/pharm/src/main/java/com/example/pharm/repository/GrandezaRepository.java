package com.example.pharm.repository;

import com.example.pharm.model.Grandeza;
import com.example.pharm.model.LogAlarme;
import com.example.pharm.model.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrandezaRepository extends JpaRepository<Grandeza, Long> {

    Page<Grandeza> findByDescricao(String descricao, Pageable pageable);

    List<Grandeza> findByStatus(StatusEnum statusEnum);


    boolean existsByDescricao(String decricao);
}
