package com.example.pharm.service;

import com.example.pharm.dto.LogProducaoDto;
import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogProducaoRepository;
import com.example.pharm.repository.ParametroRepository;
import com.example.pharm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class LogProducaoService {
    private final LogProducaoRepository logProducaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ParametroRepository parametroRepository;
    private static final Logger logger = LoggerFactory.getLogger(LogProducaoService.class);

    public LogProducaoService(LogProducaoRepository logProducaoRepository, UsuarioRepository usuarioRepository, ParametroRepository parametroRepository){
        this.logProducaoRepository = logProducaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.parametroRepository = parametroRepository;
    }

    public long contarLogProducao() {
        return logProducaoRepository.count();
    }


    public void criarLogProducao(String userLogin, Long parametroId){

        Usuario user = usuarioRepository.findByLogin(userLogin).orElseThrow(()->
                new RuntimeException("Usuario não encontrada!")
        );

        Parametro parametro = parametroRepository.findById(parametroId).orElseThrow(()->
                new RuntimeException("Parametro não encontrado!")
        );

        String strHoje = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        LogProducao l = new LogProducao();
        l.setStatus(StatusEnum.ATIVO);
        l.setParametro(parametro);
        l.setUser(user);
        l.setDataHora(strHoje);
        logProducaoRepository.save(l);
    }



        public void insertLogProducao(String userLogin, Long parametroId, String descricao) {
            insertLogProducao(userLogin, parametroId, null, descricao);
        }

        public void insertLogProducao(
                String userLogin,
                Long parametroId,
                Parametro parametroAnterior,
                String descicao
        ) {
            Usuario user = usuarioRepository.findByLogin(userLogin)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
            Parametro parametroAtual = parametroRepository.findById(parametroId)
                    .orElseThrow(() -> new RuntimeException("Parâmetro não encontrado!"));

            if (parametroAnterior != null) {
                logger.debug("parametroAnterior.id = {}, parametroAnterior.campoX = {}",
                        parametroAnterior.getId(), parametroAnterior.getValor());
            } else {
                logger.info("insertLogProducao: parametroAnterior é null");
            }

            LogProducao l = new LogProducao();
            l.setStatus(StatusEnum.ATIVO);
            l.setUser(user);
            l.setParametro(parametroAtual);
            l.setDataHora(
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
            l.setDescricao(descicao);

            // se vier um Parametro “anterior”, popula o campo
            if (parametroAnterior != null) {
                ParametroDto snapshot = ParametroDto.fromEntity(parametroAnterior);
                l.setParametroAnterior(snapshot);
            }

            logProducaoRepository.save(l);
        }


    public Page<LogProducao> listAll(Pageable pageable) {
        return logProducaoRepository.findAll(pageable);
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
