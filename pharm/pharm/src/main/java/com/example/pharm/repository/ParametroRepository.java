package com.example.pharm.repository;

import com.example.pharm.dto.ParametroOutDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.PontoControle;
import com.example.pharm.model.enumeration.FormulaEnum;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import org.hibernate.mapping.Formula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

    Optional<Parametro> findByDescricao(String descricao);

    Page<Parametro> findByFormulaEnum(FormulaEnum formulaEnum, Pageable pageable);

    List<Parametro> findByStatus(StatusEnum statusEnum);

    Optional<Parametro> findByPontoControle(PontoControle pontoControle);

    @Query("""
      SELECT new com.example.pharm.dto.ParametroOutDto(
        p.id,
        p.descricao,
        p.valor,
        p.vlMin,
        p.vlMax,
        p.status,
        u.descricao,
        g.descricao,
        p.formulaEnum,
        pc.pontoControle
        
      )
      FROM Parametro p
      JOIN p.unidade u
      JOIN p.grandeza g
      LEFT JOIN p.pontoControle pc
      WHERE p.funcaoEnum = :funcao
    """)
    List<ParametroOutDto> findAllOut(@Param("funcao") FuncaoEnum funcaoEnum);


    boolean existsByDescricao(String decricao);
}