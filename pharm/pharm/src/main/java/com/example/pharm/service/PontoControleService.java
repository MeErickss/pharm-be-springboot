package com.example.pharm.service;

import com.example.pharm.dto.PontoControleDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.PontoControle;
import com.example.pharm.model.enumeration.CLPTipoEnum;
import com.example.pharm.model.enumeration.OffsetEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoUsoEnum;
import com.example.pharm.repository.ParametroRepository;
import com.example.pharm.repository.PontoControleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PontoControleService {

    private final PontoControleRepository pontoControleRepository;

    public PontoControleService(PontoControleRepository pontoControleRepository) {
        this.pontoControleRepository = pontoControleRepository;
    }

    public long contarPontosControle() {
        return pontoControleRepository.count();
    }


    public void criarPontoControle(String pontoControle, CLPTipoEnum clpTipo, String enderecoCLP, Short tamanho, OffsetEnum offset, String descricao, StatusEnum status, TipoUsoEnum tipoUsoEnum) {
        if (pontoControleRepository.existsByPontoControle(pontoControle)) {
            throw new RuntimeException("Login de usuário já existente!");
        }

        PontoControle pc = new PontoControle();
        pc.setPontoControle(pontoControle);
        pc.setDescricao(descricao);
        pc.setClpTipo(clpTipo);
        pc.setOffset(offset);
        pc.setStatus(status);
        pc.setTamanho(tamanho);
        pc.setEnderecoCLP(enderecoCLP);
        pc.setTipoUso(tipoUsoEnum);

        pontoControleRepository.save(pc);
    }

    public void insertPontoControle(PontoControleDto dto) {
        if (pontoControleRepository.existsByPontoControle(dto.getPontoConteole())) {
            throw new RuntimeException("Login de usuário já existente!");
        }

        PontoControle pc = new PontoControle();
        pc.setPontoControle(dto.getPontoConteole());
        pc.setDescricao(dto.getDescricao());
        pc.setClpTipo(dto.getClpTipo());
        pc.setOffset(dto.getOffset());
        pc.setStatus(dto.getStatus());
        pc.setTamanho(dto.getTamanho());
        pc.setEnderecoCLP(dto.getEnderecoCLP());
        pc.setTipoUso(dto.getTipoUso());

        pontoControleRepository.save(pc);
    }


    public List<PontoControle> listAll(){
        return pontoControleRepository.findAll();
    }

    public PontoControle listId(Long id){
        return pontoControleRepository.findById(id).orElseThrow(()->
                new RuntimeException("Status '" + id + "' não encontrado")
        );
    }

    public PontoControle atualizarPontoControle(PontoControleDto dto){
        PontoControle pc = pontoControleRepository.findById(dto.getId()).orElseThrow(()->
                new RuntimeException("Usuario não encontrado")
        );

        pc.setPontoControle(dto.getPontoConteole());
        pc.setDescricao(dto.getDescricao());
        pc.setClpTipo(dto.getClpTipo());
        pc.setOffset(dto.getOffset());
        pc.setStatus(dto.getStatus());
        pc.setTamanho(dto.getTamanho());
        pc.setEnderecoCLP(dto.getEnderecoCLP());
        pc.setTipoUso(dto.getTipoUso());

        return pontoControleRepository.save(pc);
    }

    public String deletar(Long id){
        try{
            pontoControleRepository.deleteById(id);
            return "Usuario deletado com sucesso";
        } catch (Exception error){
            return "Erro ao deletar o Usuario";
        }
    }
}
