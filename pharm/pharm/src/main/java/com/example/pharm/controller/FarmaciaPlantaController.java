package com.example.pharm.controller;

import com.example.pharm.dto.FarmaciaPlantaDto;
import com.example.pharm.dto.LogProducaoDto;
import com.example.pharm.model.DistribuicaoPlanta;
import com.example.pharm.model.FarmaciaPlanta;
import com.example.pharm.model.LogProducao;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.service.DistribuicaoPlantaService;
import com.example.pharm.service.FarmaciaPlantaService;
import com.example.pharm.service.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/farmacia")
public class FarmaciaPlantaController {
    private final FarmaciaPlantaService farmaciaPlantaService;
    private final TokenService tokenService;

    public FarmaciaPlantaController(FarmaciaPlantaService farmaciaPlantaService, TokenService tokenService){
        this.farmaciaPlantaService = farmaciaPlantaService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<FarmaciaPlanta>> listAll(@CookieValue(name = "JWT", required = false) String token){
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(farmaciaPlantaService.listAll());
    }

    @PutMapping
    public ResponseEntity<FarmaciaPlanta> autualizarLogProducao(@RequestBody FarmaciaPlantaDto farmaciaPlantaDto){
        FarmaciaPlanta atualizado = farmaciaPlantaService.atualizarFarmacia(farmaciaPlantaDto);
        return ResponseEntity.ok(atualizado);
    }

}
