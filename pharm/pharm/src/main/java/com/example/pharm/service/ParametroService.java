package com.example.pharm.service;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ParametroService {

    private final ParametroRepository parametroRepository;
    private final GrandezaRepository grandezaRepository;
    private final UnidadeRepository unidadeRepository;

    public ParametroService(ParametroRepository parametroRepository,
                            GrandezaRepository grandezaRepository,
                            UnidadeRepository unidadeRepository) {
        this.parametroRepository = parametroRepository;
        this.grandezaRepository   = grandezaRepository;
        this.unidadeRepository = unidadeRepository;
    }

    public long contarParametros() {
        return parametroRepository.count();
    }

    public List<Parametro> buscarParametrosComGrandezaUnidade(FuncaoEnum funcaoEnum){
        return parametroRepository.findByFuncaoEnum(funcaoEnum);
    }

    public Parametro criarParametro(String descricao,
                               Integer valor,
                               Integer vlMin,
                               Integer vlMax,
                               StatusEnum status,
                               Long grandezaId,
                               Long unidadeId,
                               FuncaoEnum funcaoEnum
    ) {

        Grandeza g = grandezaRepository.findById(grandezaId).orElseThrow(()->
                new RuntimeException("Grandeza não encontrada!")
        );

        Unidade u = unidadeRepository.findById(unidadeId).orElseThrow(()->
                new RuntimeException("Unidade não encontrada!")
        );

        if (parametroRepository.existsByDescricao(descricao)) {
            throw new RuntimeException("Parametro já existente!");
        }
        Parametro p = new Parametro();
        p.setDescricao(descricao);
        p.setGrandeza(g);
        p.setUnidade(u);
        p.setValor(valor);
        p.setVlMin(vlMin);
        p.setVlMax(vlMax);
        p.setFuncao(funcaoEnum);
        p.setStatus(status);

        parametroRepository.save(p);
        return p;
    }

    public List<Parametro> listAll(){
        return parametroRepository.findAll();
    }

    public List<Parametro> buscarPorFuncao(FuncaoEnum funcaoEnum){
        return parametroRepository.findByFuncaoEnum(funcaoEnum);
    }

    public Parametro listId(Long id){
        return parametroRepository.findById(id).orElseThrow(()->
                new RuntimeException("Parametro não encontrado!")
        );
    }

    public Parametro atualizar(Long id, ParametroDto parametroDto){
        Parametro p = parametroRepository.findById(id).orElseThrow(()->
                new RuntimeException("Parametro não encontrado")
        );
        return parametroRepository.save(p);
    }

    public String deletar(Long id){
        try{
            parametroRepository.deleteById(id);
            return "Parametro deletado com sucesso";
        } catch (Exception error){
            return "Erro ao deletar o parametro";
        }
    }
}
