package com.example.pharm.controller;

import com.example.pharm.dto.DistribuicaoPlantaDto;
import com.example.pharm.dto.FarmaciaPlantaDto;
import com.example.pharm.model.DistribuicaoPlanta;
import com.example.pharm.model.FarmaciaPlanta;
import com.example.pharm.service.DistribuicaoPlantaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/distribuicao")
public class DistribuicaoPlantaController {
    private final DistribuicaoPlantaService distribuicaoPlantaService;

    public DistribuicaoPlantaController(DistribuicaoPlantaService distribuicaoPlantaService){
        this.distribuicaoPlantaService = distribuicaoPlantaService;
    }

    @GetMapping
    public ResponseEntity<List<DistribuicaoPlanta>> listAll(){
        return ResponseEntity.ok(distribuicaoPlantaService.listAll());
    }

    @PutMapping
    public ResponseEntity<DistribuicaoPlanta> autualizarLogProducao(@RequestBody DistribuicaoPlantaDto distribuicaoPlantaDto){
        DistribuicaoPlanta atualizado = distribuicaoPlantaService.atualizarFarmacia(distribuicaoPlantaDto);
        return ResponseEntity.ok(atualizado);
    }
}
