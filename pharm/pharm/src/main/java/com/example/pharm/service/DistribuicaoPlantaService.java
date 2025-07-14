package com.example.pharm.service;

import com.example.pharm.dto.DistribuicaoPlantaDto;
import com.example.pharm.dto.FarmaciaPlantaDto;
import com.example.pharm.model.DistribuicaoPlanta;
import com.example.pharm.model.FarmaciaPlanta;
import com.example.pharm.model.PontoControle;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoElemento;
import com.example.pharm.repository.DistribuicaoPlantaRepository;
import com.example.pharm.repository.PontoControleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistribuicaoPlantaService {
    private final DistribuicaoPlantaRepository distribuicaoPlantaRepository;
    private final PontoControleRepository pontoControleRepository;

    public DistribuicaoPlantaService(DistribuicaoPlantaRepository distribuicaoPlantaRepository, PontoControleRepository pontoControleRepository) {
        this.distribuicaoPlantaRepository = distribuicaoPlantaRepository;
        this.pontoControleRepository = pontoControleRepository;
    }

    public Long contarDistribuicao() {
        return distribuicaoPlantaRepository.count();
    }

    public List<DistribuicaoPlanta> listAll() {
        return distribuicaoPlantaRepository.findAll();
    }

    public DistribuicaoPlanta criarDistribuicaoPlanta(
            String nome,
            String nomePadronizado,
            String endereco,
            String posicaoNoLayout,
            TipoElemento tipo,
            StatusEnum statusEnum,
            Long pontoControleId
    ) {
        if (distribuicaoPlantaRepository.existsByNomePadronizado(nomePadronizado)) {
            return distribuicaoPlantaRepository.findByNomePadronizado(nomePadronizado).orElseThrow();
        }

        PontoControle p = pontoControleRepository.findById(pontoControleId).orElseThrow();;
        DistribuicaoPlanta d = new DistribuicaoPlanta();
        d.setNome(nome);
        d.setPontoControle(p);
        d.setNomePadronizado(nomePadronizado);
        d.setEndereco(endereco);
        d.setPosicaoNoLayout(posicaoNoLayout);
        d.setTipo(tipo);
        d.setStatus(statusEnum);
        return distribuicaoPlantaRepository.save(d);
    }

    public DistribuicaoPlanta atualizarFarmacia(DistribuicaoPlantaDto dto){
        DistribuicaoPlanta d = distribuicaoPlantaRepository.findById(dto.getId()).orElseThrow(()->
                new RuntimeException("Parametro não encontrado")
        );

        d.setEndereco(dto.getEndereco());
        d.setTipo(dto.getTipo());
        d.setNomePadronizado(dto.getNomePadronizado());
        d.setStatus(dto.getStatusEnum());
        d.setPosicaoNoLayout(dto.getPosicaoNoLayout());
        return distribuicaoPlantaRepository.save(d);
    }
}
