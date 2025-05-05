package com.example.pharm.repository;

import com.example.pharm.model.Unidade;
import com.example.pharm.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    // Busca por nome exato do parâmetro (case-sensitive)
    Optional<Usuario> findByLogin(String login);

    Page<Usuario>findById(Long id, Pageable pageable);

    // Busca por nome contendo (case-insensitive) com paginação
    Page<Usuario> findByLoginContainingIgnoreCase(String login, Pageable pageable);

    Optional<Unidade> findByNivelDescricao(String nivelDescricao);

    Page<Usuario> findAll(Pageable pageable);
    // Verifica se existe parâmetro com nome específico
    boolean existsByLogin(String login);
}
