package com.example.pharm.service;

import com.example.pharm.model.Grandeza;
import com.example.pharm.model.Status;
import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.GrandezaRepository;
import com.example.pharm.repository.UnidadesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GrandezasService {

    private final GrandezaRepository grandezaRepository;
    private final UnidadesRepository unidadesRepository;

    public GrandezasService(GrandezaRepository grandezaRepository,
                            UnidadesRepository unidadesRepository) {
        this.grandezaRepository = grandezaRepository;
        this.unidadesRepository = unidadesRepository;
    }

    public long contarGrandezas() {
        return grandezaRepository.count();
    }

    public void criarGrandeza(String nome, List<Long> unidadeIds, StatusEnum status) {
        // 1) Idempotência: se já existe, não faz nada
        if (grandezaRepository.existsByNome(nome)) {
            return;
        }

        // 3) Carrega as Unidades pela lista de IDs
        List<Unidade> unidades = unidadesRepository.findAllById(unidadeIds);
        if (unidades.size() != unidadeIds.size()) {
            throw new RuntimeException("Uma ou mais unidades não foram encontradas: " + unidadeIds);
        }

        // 4) Monta a Grandeza e seta associações
        Grandeza g = new Grandeza();
        g.setNome(nome);
        g.setStatus(status);
        g.setUnidades(unidades);

        // 5) Salva a Grandeza (o relacionamento @ManyToMany vai persistir a entrada na tabela intermediária)
        grandezaRepository.save(g);
    }

    /**
     * Adiciona mais unidades a uma grandeza já existente.
     */
    public void adicionarUnidades(Long grandezaId, List<Long> unidadeIds) {
        Grandeza g = grandezaRepository.findById(grandezaId)
                .orElseThrow(() -> new RuntimeException("Grandeza não encontrada: " + grandezaId));

        List<Unidade> unidades = unidadesRepository.findAllById(unidadeIds);
        if (unidades.isEmpty()) {
            throw new RuntimeException("Nenhuma unidade válida informada");
        }

        g.getUnidades().addAll(unidades);
        grandezaRepository.save(g);
    }

    public List<Grandeza> listAll(){
        return grandezaRepository.findAll();
    }

    public Grandeza listId(Long id){
        return grandezaRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }
}
