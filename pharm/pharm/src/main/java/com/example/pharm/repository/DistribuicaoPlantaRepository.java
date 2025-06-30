package com.example.pharm.repository;

import com.example.pharm.model.DistribuicaoPlanta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistribuicaoPlantaRepository extends JpaRepository<DistribuicaoPlanta, Long> {
    Optional<DistribuicaoPlanta> findByNomePadronizado(String nome);

    boolean existsByNomePadronizado(String nomePadronizado);
}
