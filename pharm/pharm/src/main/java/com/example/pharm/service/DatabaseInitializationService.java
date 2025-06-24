package com.example.pharm.service;

import com.example.pharm.model.LogArmazenamento;
import com.example.pharm.model.LogProducao;
import com.example.pharm.model.LogAlarme;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.FormulaEnum;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.repository.LogArmazenamentoRepository;
import com.example.pharm.repository.LogProducaoRepository;
import com.example.pharm.repository.LogAlarmeRepostiory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class DatabaseInitializationService implements ApplicationRunner {

    private final UsuarioService userService;
    private final GrandezaService grandezaService;
    private final UnidadeService unidadeService;
    private final ParametroService parametroService;
    private final LogProducaoRepository logProducaoRepository;
    private final LogAlarmeRepostiory logAlarmeRepostiory;
    private final LogArmazenamentoRepository logArmazenamentoRepository;

    public DatabaseInitializationService(UsuarioService userService,
                                         UnidadeService unidadeService,
                                         GrandezaService grandezaService,
                                         ParametroService parametroService,
                                         LogProducaoRepository logProducaoRepository,
                                         LogAlarmeRepostiory logAlarmeRepostiory,
                                         LogArmazenamentoRepository logArmazenamentoRepository) {
        this.userService = userService;
        this.unidadeService = unidadeService;
        this.grandezaService = grandezaService;
        this.parametroService = parametroService;
        this.logProducaoRepository = logProducaoRepository;
        this.logAlarmeRepostiory = logAlarmeRepostiory;
        this.logArmazenamentoRepository = logArmazenamentoRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (userService.contarUsuarios() == 0) {
            userService.criarUsuario("admin@gmail.com", "0000", NivelEnum.ADMIN, StatusEnum.ATIVO);
            userService.criarUsuario("manutencao@gmail.com", "1111", NivelEnum.MANUTENCAO, StatusEnum.ATIVO);
            userService.criarUsuario("operador@gmail.com", "2222", NivelEnum.OPERADOR, StatusEnum.ATIVO);
        }

        if (grandezaService.contarGrandeza() == 0) {
            grandezaService.criarGrandeza("TEMPO", StatusEnum.ATIVO);
            grandezaService.criarGrandeza("PRESSAO", StatusEnum.ATIVO);
        }

        if (unidadeService.contarUnidades() == 0) {
            unidadeService.criarUnidades("SEGUNDO", "SEG", StatusEnum.ATIVO, 1L);
            unidadeService.criarUnidades("HORA", "HR", StatusEnum.ATIVO,1L);
            unidadeService.criarUnidades("PSI", "PSI", StatusEnum.ATIVO, 2L);
        }

        if (parametroService.contarParametros() == 0) {
            parametroService.criarParametro(
                    "TEMPO PARA DRENAGEM DO TANQUE DE MISTURA [TQ-100]",
                    20, 10, 30, StatusEnum.ATIVO, 1L, 2L, FuncaoEnum.PRODUCAO, FormulaEnum.UM
            );
            parametroService.criarParametro(
                    "TEMPO PARA DRENAGEM DO TANQUE DE ADIÇÃO [TQ-200]",
                    30, 15, 45, StatusEnum.ATIVO, 1L, 1L, FuncaoEnum.PRODUCAO, FormulaEnum.DOIS
            );
            parametroService.criarParametro(
                    "TEMPO PARA DRENAGEM DO TANQUE TQ-300",
                    40, 5, 200, StatusEnum.ATIVO, 1L, 1L, FuncaoEnum.PRODUCAO, FormulaEnum.TRES
            );
            parametroService.criarParametro(
                    "TEMPO PARA DRENAGEM DO TANQUE TQ-310",
                    10, 5, 100, StatusEnum.ATIVO, 2L, 3L, FuncaoEnum.ARMAZENAMENTO, FormulaEnum.UM
            );
        }

        // -------- Inserir 100 Logs de Produção --------
        if (logProducaoRepository.count() == 0) {
            List<Usuario> usuarios = userService.listAll();
            Random random = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (int i = 1; i <= 100; i++) {
                LogProducao log = new LogProducao();
                Usuario randomUser = usuarios.get(random.nextInt(usuarios.size()));
                log.setUser(randomUser);
                log.setDescricao("Log de evento nº " + i);
                log.setDataHora(LocalDateTime.now()
                        .minusDays(random.nextInt(30))
                        .minusMinutes(random.nextInt(60))
                        .format(formatter));
                log.setStatus(StatusEnum.values()[random.nextInt(StatusEnum.values().length)]);
                logProducaoRepository.save(log);
            }
            System.out.println("100 registros de LogProducao inseridos.");
        }

        // -------- Inserir 100 Logs de Alarme --------
        if (logAlarmeRepostiory.count() == 0) {
            List<Usuario> usuarios = userService.listAll();
            Random random = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (int i = 1; i <= 100; i++) {
                LogAlarme log = new LogAlarme();
                Usuario randomUser = usuarios.get(random.nextInt(usuarios.size()));
                log.setUser(randomUser);
                log.setDescricao("Alarme crítico nº " + i);
                log.setDataHora(LocalDateTime.now()
                        .minusDays(random.nextInt(30))
                        .minusHours(random.nextInt(24))
                        .format(formatter));
                log.setStatus(StatusEnum.values()[random.nextInt(StatusEnum.values().length)]);
                logAlarmeRepostiory.save(log);
            }
            System.out.println("100 registros de LogAlarme inseridos.");
        }
        // -------- Inserir 100 Logs de Armazenamento --------
        if (logArmazenamentoRepository.count() == 0) {
            List<Usuario> usuarios = userService.listAll();
            Random random = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (int i = 1; i <= 100; i++) {
                LogArmazenamento log = new LogArmazenamento();
                Usuario randomUser = usuarios.get(random.nextInt(usuarios.size()));
                log.setUser(randomUser);
                log.setDescricao("Evento de armazenamento nº " + i);
                log.setDatahora(LocalDateTime.now()
                        .minusDays(random.nextInt(30))
                        .minusHours(random.nextInt(24))
                        .format(formatter));
                log.setStatus(StatusEnum.values()[random.nextInt(StatusEnum.values().length)]);
                logArmazenamentoRepository.save(log);
            }
            System.out.println("100 registros de LogArmazenamento inseridos.");
        }
    }
}
