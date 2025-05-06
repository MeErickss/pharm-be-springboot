package com.example.pharm.service;

import com.example.pharm.model.enumeration.FuncaoEnum;
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

    private final UsuarioService userService;
    private final GrandezaService grandezaService;
    private final UnidadeService unidadeService;
    private final ParametroService parametroService;

    public DatabaseInitializationService(UsuarioService userService,
                                         UnidadeService unidadeService,
                                         GrandezaService grandezaService,
                                         ParametroService parametroService) {
        this.userService = userService;
        this.unidadeService = unidadeService;
        this.grandezaService = grandezaService;
        this.parametroService = parametroService;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (userService.contarUsuarios() == 0) {
            userService.criarUsuario("admin@gmail.com", "0000", NivelEnum.ADMIN,StatusEnum.ATIVO);
            userService.criarUsuario("maintenance@gmail.com", "1111", NivelEnum.MANUTENCAO, StatusEnum.ATIVO);
            userService.criarUsuario("operator@gmail.com", "2222", NivelEnum.OPERADOR, StatusEnum.ATIVO);
        }
        if (unidadeService.contarUnidades() == 0) {
            unidadeService.criarUnidades("SEGUNDO", "SEG", StatusEnum.ATIVO);
            unidadeService.criarUnidades("HORA",   "HR",  StatusEnum.ATIVO);
            unidadeService.criarUnidades("PSI",    "PSI", StatusEnum.ATIVO);
        }

        // --- Grandezas e suas Unidades ---
        if (grandezaService.contarGrandeza() == 0) {
            // conforme seus INSERT IGNORE:
            // GRANDEZA 1 = TEMPO  -> Unidade 1 (SEGUNDO)
            grandezaService.criarGrandeza("TEMPO", StatusEnum.ATIVO);

            // GRANDEZA 2 = PRESSAO -> Unidade 1 (SEGUNDO) ou 3 (PSI)?
            // No seu SQL você usou unidade 1, então:
            grandezaService.criarGrandeza("PRESSAO", StatusEnum.ATIVO);

            grandezaService.adicionarUnidades(1L,2L);
            grandezaService.adicionarUnidades(1L,1L);

            grandezaService.adicionarUnidades(2L,3L);

            // --- Parâmetros ---
            if (parametroService.contarParametros() == 0) {
                parametroService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE DE MISTURA [TQ-100]",
                        20, 10, 30, StatusEnum.ATIVO, 1L, 2L, FuncaoEnum.PRODUCAO
                );
                parametroService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE DE ADIÇÃO [TQ-200]",
                        30, 15, 45, StatusEnum.ATIVO, 2L, 1L,FuncaoEnum.PRODUCAO
                );
                parametroService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE TQ-300",
                        40, 5, 200, StatusEnum.ATIVO, 1L, 1L,FuncaoEnum.PRODUCAO
                );
                parametroService.criarParametro(
                        "TEMPO PARA DRENAGEM DO TANQUE TQ-310",
                        10, 5, 100, StatusEnum.ATIVO, 1L, 2L,FuncaoEnum.ARMAZENAMENTO
                );

            }
        }
    }
}
