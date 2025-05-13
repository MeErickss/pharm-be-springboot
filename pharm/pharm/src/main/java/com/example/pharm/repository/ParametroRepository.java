package com.example.pharm.repository;

import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

    Page<Parametro> findByDescricao(String descricao, Pageable pageable);

    List<Parametro> findByFuncaoEnum(FuncaoEnum funcaoEnum);

    List<Parametro> findByStatus(StatusEnum statusEnum);

    boolean existsByDescricao(String decricao);
}