package com.example.pharm.service;

import com.example.pharm.dto.FarmaciaPlantaDto;
import com.example.pharm.model.FarmaciaPlanta;
import com.example.pharm.model.Grandeza;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoElemento;
import com.example.pharm.repository.FarmaciaPlantaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaPlantaService {
    private final FarmaciaPlantaRepository farmaciaPlantaRepository;

    public FarmaciaPlantaService(FarmaciaPlantaRepository farmaciaPlantaRepository) {
        this.farmaciaPlantaRepository = farmaciaPlantaRepository;
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
            StatusEnum statusEnum
    ) {
        // Opcional: verificar se já existe
        if (farmaciaPlantaRepository.existsByNomePadronizado(nomePadronizado)) {
            // Pode lançar exceção ou simplesmente retornar o existente, conforme sua política
            return farmaciaPlantaRepository.findByNomePadronizado(nomePadronizado).orElseThrow();
        }
        FarmaciaPlanta d = new FarmaciaPlanta();
        d.setNome(nome);
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
}
