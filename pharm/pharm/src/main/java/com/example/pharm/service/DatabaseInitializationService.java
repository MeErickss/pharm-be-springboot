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
    private final PontoControleService pontoControleService;

    public DatabaseInitializationService(UsuarioService userService,
                                         UnidadeService unidadeService,
                                         GrandezaService grandezaService,
                                         ParametroService parametroService,
                                         LogProducaoRepository logProducaoRepository,
                                         LogAlarmeRepostiory logAlarmeRepostiory,
                                         LogArmazenamentoRepository logArmazenamentoRepository,
                                         DistribuicaoPlantaService distribuicaoPlantaService,
                                         FarmaciaPlantaService farmaciaPlantaService,
                                         PontoControleService pontoControleService) {
        this.userService = userService;
        this.unidadeService = unidadeService;
        this.grandezaService = grandezaService;
        this.parametroService = parametroService;
        this.logProducaoRepository = logProducaoRepository;
        this.logAlarmeRepostiory = logAlarmeRepostiory;
        this.logArmazenamentoRepository = logArmazenamentoRepository;
        this.distribuicaoPlantaService = distribuicaoPlantaService;
        this.farmaciaPlantaService = farmaciaPlantaService;
        this.pontoControleService = pontoControleService;
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
            unidadeService.criarUnidades("HORA", "HR", StatusEnum.ATIVO, 1L);
            unidadeService.criarUnidades("PSI", "PSI", StatusEnum.ATIVO, 2L);
        }
        if (pontoControleService.contarPontosControle() == 0) {

            pontoControleService.criarPontoControle( // 1
                    "TAG_ENDERECO_0",
                    CLPTipoEnum.INTEGER,
                    "0",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "STATUS GERAL",
                    StatusEnum.ATIVO, TipoUsoEnum.PARAMETRO
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_1",
                    CLPTipoEnum.INTEGER,
                    "1",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_101",
                    StatusEnum.DESLIGADO, TipoUsoEnum.PARAMETRO
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_2",
                    CLPTipoEnum.INTEGER,
                    "2",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_102",
                    StatusEnum.DESLIGADO, TipoUsoEnum.PARAMETRO
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_15",
                    CLPTipoEnum.INTEGER,
                    "15",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "TP PARA DRENAGEM DO TANQUE DE ADICAO [TQ-200]",
                    StatusEnum.ATIVO, TipoUsoEnum.PARAMETRO
            );

            // e para os blocos seguintes (101, 102, …)
            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_101",
                    CLPTipoEnum.INTEGER,
                    "101",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_301",
                    StatusEnum.DESLIGADO, TipoUsoEnum.PARAMETRO
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_102",
                    CLPTipoEnum.INTEGER,
                    "102",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_302",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle( // 7
                    "TAG_ENDERECO_103",
                    CLPTipoEnum.INTEGER,
                    "103",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_302",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_104",
                    CLPTipoEnum.INTEGER,
                    "104",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_304",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_105",
                    CLPTipoEnum.INTEGER,
                    "105",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_305",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_106",
                    CLPTipoEnum.INTEGER,
                    "106",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_306",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_107",
                    CLPTipoEnum.INTEGER,
                    "107",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_307",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_108",
                    CLPTipoEnum.INTEGER,
                    "108",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_308",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_109",
                    CLPTipoEnum.INTEGER,
                    "109",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_309",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle( // 14
                    "TAG_ENDERECO_110",
                    CLPTipoEnum.INTEGER,
                    "110",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_310",
                    StatusEnum.ATIVO, TipoUsoEnum.SAIDA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_111",
                    CLPTipoEnum.INTEGER,
                    "111",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_311",
                    StatusEnum.ATIVO, TipoUsoEnum.SAIDA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_112",
                    CLPTipoEnum.INTEGER,
                    "112",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_312",
                    StatusEnum.ATIVO, TipoUsoEnum.SAIDA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_113",
                    CLPTipoEnum.INTEGER,
                    "113",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_313",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_114",
                    CLPTipoEnum.INTEGER,
                    "114",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_314",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_115",
                    CLPTipoEnum.INTEGER,
                    "115",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_315",
                    StatusEnum.ATIVO, TipoUsoEnum.SAIDA
            );

            pontoControleService.criarPontoControle( // 20
                    "TAG_ENDERECO_116",
                    CLPTipoEnum.INTEGER,
                    "116",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_316",
                    StatusEnum.ATIVO, TipoUsoEnum.SAIDA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_117",
                    CLPTipoEnum.INTEGER,
                    "117",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_317",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_118",
                    CLPTipoEnum.INTEGER,
                    "118",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_318",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_119",
                    CLPTipoEnum.INTEGER,
                    "119",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_319",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_120",
                    CLPTipoEnum.INTEGER,
                    "120",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_320",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );
            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_121",
                    CLPTipoEnum.INTEGER,
                    "121",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_321",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_122",
                    CLPTipoEnum.INTEGER,
                    "122",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_322",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle( // 27
                    "TAG_ENDERECO_123",
                    CLPTipoEnum.INTEGER,
                    "123",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_323",
                    StatusEnum.ATIVO, TipoUsoEnum.SAIDA
            );

            pontoControleService.criarPontoControle(
                    "TAG_ENDERECO_124",
                    CLPTipoEnum.INTEGER,
                    "124",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_324",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle( // 29
                    "TAG_ENDERECO_125",
                    CLPTipoEnum.INTEGER,
                    "125",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_325",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

            pontoControleService.criarPontoControle( // 30
                    "TAG_ENDERECO_126",
                    CLPTipoEnum.INTEGER,
                    "126",
                    (short) 1,
                    OffsetEnum.OFFSET_1,
                    "VALVULA XV_325",
                    StatusEnum.ATIVO, TipoUsoEnum.ENTRADA
            );

        }

        if (parametroService.contarParametros() == 0) {
            parametroService.criarParametro(
                    "TEMPO PARA DRENAGEM DO TANQUE DE MISTURA [TQ-100]",
                    20, 10, 30, StatusEnum.ATIVO, 1L, 2L, FuncaoEnum.PRODUCAO, FormulaEnum.UM, 1L
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
                    10, 5, 100, StatusEnum.ATIVO, 2L, 3L, FuncaoEnum.ARMAZENAMENTO, FormulaEnum.UM, 4L
            );
        }

            // Inicializa FarmaciaPlanta se ainda não houver registros
            if (farmaciaPlantaService.contarDistribuicao() == 0) {
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "V1",
                        "valvula1-1",
                        "valvula1-1", // placeholder de endereço; ajuste conforme mapeamento real
                        "{\"x\":112,\"y\":277,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 6L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "V2",
                        "valvula1-2",
                        "valvula1-2",
                        "{\"x\":27,\"y\":90,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 7L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "V3",
                        "valvula1-3",
                        "valvula1-3",
                        "{\"x\":440,\"y\":173,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 8L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "V4",
                        "valvula1-4",
                        "valvula1-4",
                        "{\"x\":394,\"y\":213,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 9L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "V5",
                        "valvula1-5",
                        "valvula1-5",
                        "{\"x\":438,\"y\":324,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 10L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "V6",
                        "valvula1-6",
                        "valvula1-6",
                        "{\"x\":502,\"y\":325,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 11L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "V7",
                        "valvula2",
                        "valvula2",
                        "{\"x\":91,\"y\":305,\"w\":40,\"h\":40}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 12L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "S4",
                        "sensor1",
                        "sensor1",
                        "{\"x\":312,\"y\":80,\"w\":50,\"h\":50}",
                        TipoElemento.SENSOR,
                        StatusEnum.DESLIGADO, 14L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "S1",
                        "sensor2-1",
                        "sensor2-1",
                        "{\"x\":160,\"y\":142,\"w\":32,\"h\":40}",
                        TipoElemento.SENSOR,
                        StatusEnum.DESLIGADO, 15L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "S2",
                        "sensor2-2",
                        "sensor2-2",
                        "{\"x\":160,\"y\":172,\"w\":32,\"h\":40}",
                        TipoElemento.SENSOR,
                        StatusEnum.DESLIGADO, 16L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "S3",
                        "sensor2-3",
                        "sensor2-3",
                        "{\"x\":160,\"y\":207,\"w\":32,\"h\":40}",
                        TipoElemento.SENSOR,
                        StatusEnum.DESLIGADO, 17L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "B1",
                        "bomba",
                        "bomba",
                        "{\"x\":280,\"y\":305,\"w\":80,\"h\":80}",
                        TipoElemento.BOMBA,
                        StatusEnum.DESLIGADO, 29L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "NTA",
                        "Nivel_ta",
                        "Nivel_ta",
                        "{\"x\":220,\"y\":105,\"w\":70,\"h\":135}",
                        TipoElemento.INDICADOR_VOLUME,
                        StatusEnum.DESLIGADO, 19L
                );
                farmaciaPlantaService.criarFarmaciaPlanta(
                        "NTM",
                        "Nivel_tm",
                        "Nivel_tm",
                        "{\"x\":106,\"y\":155,\"w\":50,\"h\":84}",
                        TipoElemento.INDICADOR_VOLUME,
                        StatusEnum.DESLIGADO, 20L
                );

                farmaciaPlantaService.criarFarmaciaPlanta(
                        "PBA",
                        "Pressao_ba",
                        "Pressao_ba",
                        "{\"x\":320,\"y\":222,\"w\":60,\"h\":80}",
                        TipoElemento.INDICADOR_VOLUME,
                        StatusEnum.DESLIGADO, 30L
                );

                System.out.println("FarmaciaPlanta inicializada com elementos padrão.");
            }


            if (distribuicaoPlantaService.contarDistribuicao() == 0) {
                // Suponha que você queira inserir os mesmos elementos do frontend:
                distribuicaoPlantaService.criarDistribuicaoPlanta(// 1
                        "V1",
                        "valvula1-1",
                        "valvula1-1",
                        "{\"x\":157,\"y\":54,\"w\":30,\"h\":30}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 20L
                );
                distribuicaoPlantaService.criarDistribuicaoPlanta(
                        "V2",
                        "valvula1-2",
                        "valvula1-2",
                        "{\"x\":222,\"y\":54,\"w\":30,\"h\":30}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 21L
                );
                distribuicaoPlantaService.criarDistribuicaoPlanta(
                        "V3",
                        "valvula1_c",
                        "valvula1_c",
                        "{\"x\":60,\"y\":167,\"w\":30,\"h\":30}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 22L
                );
                distribuicaoPlantaService.criarDistribuicaoPlanta(
                        "V4",
                        "valvula2-1",
                        "valvula2-1",
                        "{\"x\":126,\"y\":99,\"w\":30,\"h\":30}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 23L
                );
                distribuicaoPlantaService.criarDistribuicaoPlanta(
                        "V5",
                        "valvula2-2",
                        "valvula2-2",
                        "{\"x\":137,\"y\":259,\"w\":30,\"h\":30}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 24L
                );
                distribuicaoPlantaService.criarDistribuicaoPlanta(
                        "V6",
                        "valvula2-3",
                        "valvula2-3",
                        "{\"x\":270,\"y\":258,\"w\":30,\"h\":30}",
                        TipoElemento.VALVULA,
                        StatusEnum.DESLIGADO, 25L
                );
                distribuicaoPlantaService.criarDistribuicaoPlanta(
                        "S1",
                        "sensor1",
                        "sensor1",
                        "{\"x\":224,\"y\":90,\"w\":50,\"h\":50}",
                        TipoElemento.SENSOR,
                        StatusEnum.DESLIGADO, 27L
                );
                distribuicaoPlantaService.criarDistribuicaoPlanta(
                        "B1",
                        "bomba2-1",
                        "bomba2-1",
                        "{\"x\":375,\"y\":300,\"w\":65,\"h\":65}",
                        TipoElemento.BOMBA,
                        StatusEnum.DESLIGADO, 26L
                );
                distribuicaoPlantaService.criarDistribuicaoPlanta(
                        "B2",
                        "bomba2-2",
                        "bomba2-2",
                        "{\"x\":376,\"y\":220,\"w\":65,\"h\":65}",
                        TipoElemento.BOMBA,
                        StatusEnum.DESLIGADO, 28L
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