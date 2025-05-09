package com.example.pharm.service;

import com.example.pharm.dto.LogArmazenamentoDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogArmazenamentoRepository;
import com.example.pharm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LogArmazenamentoService {
    private final LogArmazenamentoRepository logArmazenamentoRepository;
    private final UsuarioRepository usuarioRepository;

    public LogArmazenamentoService(LogArmazenamentoRepository logArmazenamentoRepository, UsuarioRepository usuarioRepository){
        this.logArmazenamentoRepository = logArmazenamentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public long contarLogArmazenamento() {
        return logArmazenamentoRepository.count();
    }


    public LogArmazenamento criarLogArmazenamento(Long userId, String descricao, StatusEnum status, String dataHora){

        Usuario user = usuarioRepository.findById(userId).orElseThrow(()->
                new RuntimeException("Usuario não encontrado!")
        );

        LogArmazenamento l = new LogArmazenamento();
        l.setStatus(status);
        l.setDescricao(descricao);
        l.setUser(user);
        l.setDatahora(dataHora);
        logArmazenamentoRepository.save(l);
        return l;
    }

    public List<LogArmazenamento> listAll(){
        return logArmazenamentoRepository.findAll();
    }

    public LogArmazenamento listId(Long id){
        return logArmazenamentoRepository.findById(id).orElseThrow(()->
                new RuntimeException("LogArmazenamento não encontrado!")
        );
    }

    public LogArmazenamento atualizar(Long id, LogArmazenamentoDto logArmazenamentoDto){
        LogArmazenamento l = logArmazenamentoRepository.findById(id).orElseThrow(()->
                new RuntimeException("LogArmazenamento não encontrado")
        );
        return logArmazenamentoRepository.save(l);
    }

    public String deletar(Long id){
        try{
            logArmazenamentoRepository.deleteById(id);
            return "LogArmazenamento deletado com sucesso";
        } catch (Exception error){
            return "Erro ao deletar o LogArmazenamento";
        }
    }
}
