package com.example.pharm.repository;

import com.example.pharm.model.Parametros;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametrosRepository extends JpaRepository<Parametros, Long> {

    // Busca por nome exato do parâmetro (case-sensitive)
    List<Parametros> findByParametro(String parametro);

    // Busca por nome contendo (case-insensitive) com paginação
    Page<Parametros> findByParametroContainingIgnoreCase(String parametro, Pageable pageable);

    // Busca por status
    List<Parametros> findByStatusDescricao(String statusDescricao);

    // Consulta personalizada com JPQL para parâmetros com funções específicas
    @Query("SELECT p FROM Parametros p JOIN p.funcoes f WHERE f.nome = :nomeFuncao")
    List<Parametros> findByFuncao(@Param("nomeFuncao") String nomeFuncao);

    // Consulta nativa para parâmetros com unidade específica
    @Query(value = "SELECT p.* FROM parametros p " +
            "JOIN parametros_unidades pu ON p.id = pu.id_parametros " +
            "JOIN unidades u ON pu.id_unidades = u.id " +
            "WHERE u.sigla = :siglaUnidade", nativeQuery = true)
    List<Parametros> findByUnidade(@Param("siglaUnidade") String siglaUnidade);

    // Verifica se existe parâmetro com nome específico
    boolean existsByParametro(String parametro);
}