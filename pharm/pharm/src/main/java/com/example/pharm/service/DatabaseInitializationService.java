package com.example.pharm.service;

import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DatabaseInitializationService implements ApplicationRunner {

    private final UsuariosService userService;
    private final GrandezasService grandezasService;
    private final UnidadesService unidadesService;
    private final ParametrosService parametrosService;

    public DatabaseInitializationService(UsuariosService userService,
                                         UnidadesService unidadesService,
                                         GrandezasService grandezasService,
                                         ParametrosService parametrosService) {
        this.userService = userService;
        this.unidadesService = unidadesService;
        this.grandezasService = grandezasService;
        this.parametrosService = parametrosService;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (userService.contarUsuarios() == 0) {
            userService.criarUsuario("admin@gmail.com", "0000", NivelEnum.ADMIN,StatusEnum.ATIVO);
            userService.criarUsuario("maintenance@gmail.com", "1111", NivelEnum.MANUTENCAO, StatusEnum.ATIVO);
            userService.criarUsuario("operator@gmail.com", "2222", NivelEnum.OPERADOR, StatusEnum.ATIVO);
        }
        if (unidadesService.contarUnidades() == 0) {
            unidadesService.criarUnidades("SEGUNDO", "SEG", StatusEnum.ATIVO);
            unidadesService.criarUnidades("HORA",   "HR",  StatusEnum.ATIVO);
            unidadesService.criarUnidades("PSI",    "PSI", StatusEnum.ATIVO);
        }

        // --- Grandezas e suas Unidades ---
        if (grandezasService.contarGrandezas() == 0) {
            // conforme seus INSERT IGNORE:
            // GRANDEZA 1 = TEMPO  -> Unidade 1 (SEGUNDO)
            grandezasService.criarGrandeza(
                    "TEMPO",
                    List.of(1L),       // lista de IDs das unidades
                    StatusEnum.ATIVO
            );

            // GRANDEZA 2 = PRESSAO -> Unidade 1 (SEGUNDO) ou 3 (PSI)?
            // No seu SQL você usou unidade 1, então:
            grandezasService.criarGrandeza(
                    "PRESSAO",
                    List.of(1L),
                    StatusEnum.ATIVO
            );

            // --- Parâmetros ---
            if (parametrosService.contarParametros() == 0) {
                parametrosService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE DE MISTURA [TQ-100]",
                        20, 10, 30, StatusEnum.ATIVO
                );
                parametrosService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE DE ADIÇÃO [TQ-200]",
                        30, 15, 45, StatusEnum.ATIVO
                );
                parametrosService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE TQ-300",
                        40, 5, 200, StatusEnum.ATIVO
                );
                parametrosService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE TQ-310",
                        10, 5, 100, StatusEnum.ATIVO
                );

                parametrosService.setFuncao(1L,1L);
                parametrosService.setUnidade(1L,2L);
                parametrosService.setGrandeza(1L,1L);

                parametrosService.setFuncao(2L,2L);
                parametrosService.setUnidade(2L,1L);
                parametrosService.setGrandeza(2L,2L);

                parametrosService.setFuncao(3L,2L);
                parametrosService.setUnidade(3L,2L);
                parametrosService.setGrandeza(3L,1L);

            }
        }
    }
}
