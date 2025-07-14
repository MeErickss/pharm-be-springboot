package com.example.pharm.service;

import com.example.pharm.dto.FarmaciaPlantaDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoElemento;
import com.example.pharm.repository.FarmaciaPlantaRepository;
import com.example.pharm.repository.PontoControleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaPlantaService {
    private final FarmaciaPlantaRepository farmaciaPlantaRepository;
    private final PontoControleRepository pontoControleRepository;

    public FarmaciaPlantaService(FarmaciaPlantaRepository farmaciaPlantaRepository, PontoControleRepository pontoControleRepository) {
        this.farmaciaPlantaRepository = farmaciaPlantaRepository;
        this.pontoControleRepository = pontoControleRepository;
    }

    public Long contarDistribuicao() {
        return farmaciaPlantaRepository.count();
    }

    public List<FarmaciaPlanta> listAll() {
        return farmaciaPlantaRepository.findAll();
    }

    public FarmaciaPlanta criarFarmaciaPlanta(
            String nome,
            String nomePadronizado,
            String endereco,
            String posicaoNoLayout,
            TipoElemento tipo,
            StatusEnum statusEnum,
            Long pontoControleId
    ) {
        // Opcional: verificar se já existe
        if (farmaciaPlantaRepository.existsByNomePadronizado(nomePadronizado)) {
            // Pode lançar exceção ou simplesmente retornar o existente, conforme sua política
            return farmaciaPlantaRepository.findByNomePadronizado(nomePadronizado).orElseThrow();
        }

        PontoControle p = pontoControleRepository.findById(pontoControleId).orElseThrow();
        FarmaciaPlanta d = new FarmaciaPlanta();
        d.setNome(nome);
        d.setPontoControle(p);
        d.setNomePadronizado(nomePadronizado);
        d.setEndereco(endereco);
        d.setPosicaoNoLayout(posicaoNoLayout);
        d.setTipo(tipo);
        d.setStatus(statusEnum);
        return farmaciaPlantaRepository.save(d);
    }

    public FarmaciaPlanta atualizarFarmacia(FarmaciaPlantaDto dto){
        FarmaciaPlanta f = farmaciaPlantaRepository.findById(dto.getId()).orElseThrow(()->
                new RuntimeException("Parametro não encontrado")
        );

        f.setEndereco(dto.getEndereco());
        f.setTipo(dto.getTipo());
        f.setNomePadronizado(dto.getNomePadronizado());
        f.setStatus(dto.getStatusEnum());
        f.setPosicaoNoLayout(dto.getPosicaoNoLayout());
        return farmaciaPlantaRepository.save(f);
    }

    public FarmaciaPlanta atualizarStatusFarmacia(Long id, StatusEnum status){
        FarmaciaPlanta f = farmaciaPlantaRepository.findById(id).orElseThrow(()->
                new RuntimeException("Parametro não encontrado")
        );


        f.setStatus(status);
        return farmaciaPlantaRepository.save(f);
    }
}
