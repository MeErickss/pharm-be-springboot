package com.example.pharm.repository;

import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findByLogin(String login, Pageable pageable);

    List<Usuario> findByStatus(StatusEnum statusEnum);

    boolean existsByLogin(String login);
}
