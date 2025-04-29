package com.example.pharm.repository;

import com.example.pharm.model.Unidades;
import com.example.pharm.model.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    // Busca por nome exato do parâmetro (case-sensitive)
    Optional<Usuarios> findByLogin(String login);

    Optional<Usuarios>findById(Long id);

    // Busca por nome contendo (case-insensitive) com paginação
    Page<Usuarios> findByLoginContainingIgnoreCase(String login, Pageable pageable);

    // Busca por status
    Optional<Usuarios> findByStatusDescricao(String statusDescricao);

    Optional<Unidades> findByNivelDescricao(String nivelDescricao);

    Page<Usuarios> findAll(Pageable pageable);
    // Verifica se existe parâmetro com nome específico
    boolean existsByLogin(String login);
}
