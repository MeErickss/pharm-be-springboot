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

    // dentro de ParametroRepository
    @Query(value = "SELECT p.id, p.descricao, p.valor, p.vl_min as vlMin, p.vl_max as vlMax, " +
            "p.status, p.funcao_enum as funcaoEnum, " +
            "u.descricao as unidadeDescricao, g.descricao as grandezaDescricao " +
            "FROM parametro p " +
            "INNER JOIN unidade u ON p.id_unidade = u.id " +
            "INNER JOIN grandeza g ON p.id_grandeza = g.id",
            nativeQuery = true)
    List<Parametro> findAllParametrosWithUnidadeAndGrandeza();


    boolean existsByDescricao(String decricao);
}