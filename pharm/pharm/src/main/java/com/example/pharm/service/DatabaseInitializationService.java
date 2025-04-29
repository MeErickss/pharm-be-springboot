package com.example.pharm.service;

import com.example.pharm.model.Niveis;
import com.example.pharm.model.Status;
import com.example.pharm.repository.GrandezaRepository;
import com.example.pharm.repository.UnidadesRepository;
import com.example.pharm.service.FuncoesService;
import com.example.pharm.service.StatusService;
import com.example.pharm.service.UsuariosService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DatabaseInitializationService implements ApplicationRunner {

    private final StatusService statusService;
    private final FuncoesService funcoesService;
    private final UsuariosService userService;
    private final GrandezasService grandezasService;
    private final UnidadesService unidadesService;
    private final NiveisService niveisService;
    private final ParametrosService parametrosService;

    public DatabaseInitializationService(StatusService statusService,
                                         FuncoesService funcoesService,
                                         UsuariosService userService,
                                         UnidadesService unidadesService,
                                         GrandezasService grandezasService,
                                         NiveisService niveisService,
                                         ParametrosService parametrosService) {
        this.statusService = statusService;
        this.funcoesService = funcoesService;
        this.userService = userService;
        this.grandezasService = grandezasService;
        this.unidadesService = unidadesService;
        this.niveisService = niveisService;
        this.parametrosService = parametrosService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (statusService.contarStatus() == 0) {
            statusService.criarStatus("ATIVO");
            statusService.criarStatus("INATIVO");
            statusService.criarStatus("BLOQUEADO");
            statusService.criarStatus("RETIDO");
        }

        if (funcoesService.contarFuncoes() == 0) {
            funcoesService.criarFuncao("PRODUCAO", "ATIVO");
            funcoesService.criarFuncao("ARMAZENAMENTO", "ATIVO");
        }

        if (niveisService.contarNiveis() == 0) {
            niveisService.criarNiveis("ADMIN", "ATIVO");
            niveisService.criarNiveis("MANUTENCAO",  "ATIVO");
            niveisService.criarNiveis("OPERADOR", "ATIVO");
        }

        if (userService.contarUsuarios() == 0) {
            userService.criarUsuario("admin@gmail.com", "0000", 3L, "ATIVO");
            userService.criarUsuario("maintenance@gmail.com", "1111", 2L, "ATIVO");
            userService.criarUsuario("operator@gmail.com", "2222", 1L, "ATIVO");
        }
        if (unidadesService.contarUnidades() == 0) {
            unidadesService.criarUnidades("SEGUNDO", "SEG", "ATIVO");
            unidadesService.criarUnidades("HORA",   "HR",  "ATIVO");
            unidadesService.criarUnidades("PSI",    "PSI", "ATIVO");
        }

        // --- Grandezas e suas Unidades ---
        if (grandezasService.contarGrandezas() == 0) {
            // conforme seus INSERT IGNORE:
            // GRANDEZA 1 = TEMPO  -> Unidade 1 (SEGUNDO)
            grandezasService.criarGrandeza(
                    "TEMPO",
                    List.of(1L),       // lista de IDs das unidades
                    "ATIVO"
            );

            // GRANDEZA 2 = PRESSAO -> Unidade 1 (SEGUNDO) ou 3 (PSI)?
            // No seu SQL você usou unidade 1, então:
            grandezasService.criarGrandeza(
                    "PRESSAO",
                    List.of(1L),
                    "ATIVO"
            );

            // --- Parâmetros ---
            if (parametrosService.contarParametros() == 0) {
                parametrosService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE DE MISTURA [TQ-100]",
                        20, 10, 30, "ATIVO"
                );
                parametrosService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE DE ADIÇÃO [TQ-200]",
                        30, 15, 45, "ATIVO"
                );
                parametrosService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE TQ-300",
                        40, 5, 200, "ATIVO"
                );
                parametrosService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE TQ-310",
                        10, 5, 100, "ATIVO"
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
