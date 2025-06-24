package com.example.pharm.service;

import com.example.pharm.model.*;
import com.example.pharm.model.enumeration.*;
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
    private final DistribuicaoPlantaService distribuicaoPlantaService;
    private final FarmaciaPlantaService farmaciaPlantaService;

    public DatabaseInitializationService(UsuarioService userService,
                                         UnidadeService unidadeService,
                                         GrandezaService grandezaService,
                                         ParametroService parametroService,
                                         LogProducaoRepository logProducaoRepository,
                                         LogAlarmeRepostiory logAlarmeRepostiory,
                                         LogArmazenamentoRepository logArmazenamentoRepository,
                                         DistribuicaoPlantaService distribuicaoPlantaService,
                                         FarmaciaPlantaService farmaciaPlantaService) {
        this.userService = userService;
        this.unidadeService = unidadeService;
        this.grandezaService = grandezaService;
        this.parametroService = parametroService;
        this.logProducaoRepository = logProducaoRepository;
        this.logAlarmeRepostiory = logAlarmeRepostiory;
        this.logArmazenamentoRepository = logArmazenamentoRepository;
        this.distribuicaoPlantaService = distribuicaoPlantaService;
        this.farmaciaPlantaService = farmaciaPlantaService;
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

            // Inicializa FarmaciaPlanta se ainda não houver registros
            if (farmaciaPlantaService.contarDistribuicao() == 0) {
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "valvula1-1",
                        "valvula1-1", // placeholder de endereço; ajuste conforme mapeamento real
                        "{\"x\":112,\"y\":277,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "valvula1-2",
                        "valvula1-2",
                        "{\"x\":27,\"y\":90,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "valvula1-3",
                        "valvula1-3",
                        "{\"x\":440,\"y\":173,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "valvula1-4",
                        "valvula1-4",
                        "{\"x\":394,\"y\":213,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "valvula1-5",
                        "valvula1-5",
                        "{\"x\":438,\"y\":324,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "valvula1-6",
                        "valvula1-6",
                        "{\"x\":502,\"y\":325,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "valvula2",
                        "valvula2",
                        "{\"x\":91,\"y\":305,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "sensor1",
                        "sensor1",
                        "{\"x\":312,\"y\":80,\"w\":50,\"h\":50}",
                        TipoElemento.SENSOR,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "sensor2-1",
                        "sensor2-1",
                        "{\"x\":160,\"y\":142,\"w\":32,\"h\":40}",
                        TipoElemento.SENSOR,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "sensor2-2",
                        "sensor2-2",
                        "{\"x\":160,\"y\":172,\"w\":32,\"h\":40}",
                        TipoElemento.SENSOR,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "sensor2-3",
                        "sensor2-3",
                        "{\"x\":160,\"y\":207,\"w\":32,\"h\":40}",
                        TipoElemento.SENSOR,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "bomba",
                        "bomba",
                        "{\"x\":280,\"y\":305,\"w\":80,\"h\":80}",
                        TipoElemento.BOMBA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "bomba-2",
                        "bomba-2",
                        "{\"x\":280,\"y\":305,\"w\":80,\"h\":80}",
                        TipoElemento.BOMBA,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "Nivel_ta",
                        "Nivel_ta",
                        "{\"x\":220,\"y\":105,\"w\":70,\"h\":135}",
                        TipoElemento.INDICADOR_VOLUME,
                        StatusEnum.ATIVO
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "Nivel_tm",
                        "Nivel_tm",
                        "{\"x\":106,\"y\":155,\"w\":50,\"h\":84}",
                        TipoElemento.INDICADOR_VOLUME,
                        StatusEnum.ATIVO
                );

                System.out.println("FarmaciaPlanta inicializada com elementos padrão.");
            }


        if (distribuicaoPlantaService.contarDistribuicao() == 0) {
            // Suponha que você queira inserir os mesmos elementos do frontend:
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "valvula1-1",
                    "valvula1-1",                       // placeholder de endereço; ajuste se tiver outro mapeamento
                    "{\"x\":157,\"y\":54,\"w\":30,\"h\":30}", // posicaoNoLayout em JSON
                    TipoElemento.VALVULA,
                    StatusEnum.ATIVO
            );
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "valvula1-2",
                    "valvula1-2",
                    "{\"x\":222,\"y\":54,\"w\":30,\"h\":30}",
                    TipoElemento.VALVULA,
                    StatusEnum.ATIVO
            );
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "valvula1_c",
                    "valvula1_c",
                    "{\"x\":60,\"y\":167,\"w\":30,\"h\":30}",
                    TipoElemento.VALVULA,
                    StatusEnum.ATIVO
            );
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "valvula2-1",
                    "valvula2-1",
                    "{\"x\":126,\"y\":99,\"w\":30,\"h\":30}",
                    TipoElemento.VALVULA,
                    StatusEnum.ATIVO
            );
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "valvula2-2",
                    "valvula2-2",
                    "{\"x\":137,\"y\":259,\"w\":30,\"h\":30}",
                    TipoElemento.VALVULA,
                    StatusEnum.ATIVO
            );
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "valvula2-3",
                    "valvula2-3",
                    "{\"x\":270,\"y\":258,\"w\":30,\"h\":30}",
                    TipoElemento.VALVULA,
                    StatusEnum.ATIVO
            );
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "sensor1",
                    "sensor1",
                    "{\"x\":224,\"y\":90,\"w\":50,\"h\":50}",
                    TipoElemento.SENSOR,
                    StatusEnum.ATIVO
            );
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "bomba2-1",
                    "bomba2-1",
                    "{\"x\":375,\"y\":300,\"w\":65,\"h\":65}",
                    TipoElemento.BOMBA,
                    StatusEnum.ATIVO
            );
            distribuicaoPlantaService.criarDistribuicaoPlanta(
                    "bomba2-2",
                    "bomba2-2",
                    "{\"x\":376,\"y\":220,\"w\":65,\"h\":65}",
                    TipoElemento.BOMBA,
                    StatusEnum.ATIVO
            );
            System.out.println("DistribuicaoPlanta inicializada com elementos padrão.");
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
