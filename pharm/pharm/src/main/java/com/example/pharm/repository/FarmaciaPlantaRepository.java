package com.example.pharm.repository;
import com.example.pharm.model.FarmaciaPlanta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmaciaPlantaRepository extends JpaRepository<FarmaciaPlanta, Long> {
    Optional<FarmaciaPlanta> findByNomePadronizado(String nome);

    boolean existsByNomePadronizado(String nomePadronizado);
}
