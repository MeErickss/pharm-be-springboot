package com.example.pharm.service;

import com.example.pharm.dto.LogArmazenamentoDto;
import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogArmazenamentoRepository;
import com.example.pharm.repository.ParametroRepository;
import com.example.pharm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class LogArmazenamentoService {
    private final LogArmazenamentoRepository logArmazenamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ParametroRepository parametroRepository;

    public LogArmazenamentoService(LogArmazenamentoRepository logArmazenamentoRepository, UsuarioRepository usuarioRepository, ParametroRepository parametroRepository){
        this.logArmazenamentoRepository = logArmazenamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.parametroRepository = parametroRepository;
    }

    public long contarLogArmazenamento() {
        return logArmazenamentoRepository.count();
    }


    public LogArmazenamento insertLogArmazenamento(String userLogin, Long parametroId) {
        return insertLogArmazenamento(userLogin, parametroId, null);
    }

    public LogArmazenamento insertLogArmazenamento(
            String userLogin,
            Long parametroId,
            Parametro parametroAnterior  // pode ser null
    ) {
        Usuario user = usuarioRepository.findByLogin(userLogin)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        Parametro parametroAtual = parametroRepository.findById(parametroId)
                .orElseThrow(() -> new RuntimeException("Parâmetro não encontrado!"));

        LogArmazenamento l = new LogArmazenamento();
        l.setStatus(StatusEnum.ATIVO);
        l.setUser(user);
        l.setParametro(parametroAtual);
        l.setDatahora(
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        l.setDescricao("Log de Armazenamento");

        if (parametroAnterior != null) {
            l.setParametroAnterior(parametroAnterior);
        }

        return logArmazenamentoRepository.save(l);
    }

    public Page<LogArmazenamento> listAll(Pageable pageable) {
        return logArmazenamentoRepository.findAll(pageable);
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
