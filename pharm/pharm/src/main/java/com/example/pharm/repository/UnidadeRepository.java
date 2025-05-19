package com.example.pharm.repository;

import com.example.pharm.model.Unidade;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    Page<Unidade> findByDescricao(String descricao, Pageable pageable);

    List<Unidade> findByStatus(StatusEnum statusEnum);

    @Query("""
      SELECT DISTINCT u
        FROM Parametro p
        JOIN p.unidade u
        JOIN p.grandeza g
       WHERE g.descricao = :descricaoGrandeza
    """)
    List<Unidade> findUnidadesByGrandezaDescricao(@Param("descricaoGrandeza") String descricaoGrandeza);


    boolean existsByDescricao(String decricao);
}

