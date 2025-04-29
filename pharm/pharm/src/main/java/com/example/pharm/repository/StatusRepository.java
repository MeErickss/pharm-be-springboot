package com.example.pharm.repository;

import com.example.pharm.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByDescricao(String descricao);

    Page<Status> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    boolean existsByDescricao(String decricao);
}
