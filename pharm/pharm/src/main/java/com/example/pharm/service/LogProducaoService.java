package com.example.pharm.service;

import com.example.pharm.dto.LogProducaoDto;
import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogProducaoRepository;
import com.example.pharm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LogProducaoService {
    private final LogProducaoRepository logProducaoRepository;
    private final UsuarioRepository usuarioRepository;

    public LogProducaoService(LogProducaoRepository logProducaoRepository, UsuarioRepository usuarioRepository){
        this.logProducaoRepository = logProducaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public long contarLogProducao() {
        return logProducaoRepository.count();
    }


    public LogProducao criarLogProducao(Long userId, String descricao, StatusEnum status, String dataHora){

        Usuario user = usuarioRepository.findById(userId).orElseThrow(()->
                new RuntimeException("Usuario não encontrada!")
        );


        LogProducao l = new LogProducao();
        l.setStatus(status);
        l.setDescricao(descricao);
        l.setUser(user);
        l.setDataHora(dataHora);
        logProducaoRepository.save(l);
        return l;
    }

    public List<LogProducao> listAll(){
        return logProducaoRepository.findAll();
    }

    public LogProducao listId(Long id){
        return logProducaoRepository.findById(id).orElseThrow(()->
                new RuntimeException("LogProducao não encontrada!")
        );
    }

    public LogProducao atualizar(Long id, LogProducaoDto logProducaoDto){
        LogProducao l = logProducaoRepository.findById(id).orElseThrow(()->
                new RuntimeException("LogProducao não encontrado")
        );
        return logProducaoRepository.save(l);
    }

    public String deletar(Long id){
        try{
            logProducaoRepository.deleteById(id);
            return "LogProducao deletado com sucesso";
        } catch (Exception error){
            return "Erro ao deletar o LogProducao";
        }
    }
}
