package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.service.ParametroService;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/parametro")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ParametroController {
    private final ParametroService parametroService;
    private final TokenService tokenService;

    public ParametroController(ParametroService parametroService, TokenService tokenService){
        this.parametroService = parametroService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<Parametro>> listAll(
            @RequestAttribute("userId") Long userId) {

        List<Parametro> todos = parametroService.listAll();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/funcao")
    public ResponseEntity<List<Parametro>> listFuncao(
            @CookieValue(name = "JWT", required = false) String token,
            @RequestParam("funcao") FuncaoEnum funcaoEnum) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = tokenService.validarToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Parametro> todos = parametroService.buscarPorFuncao(funcaoEnum);
        return ResponseEntity.ok(todos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Parametro> buscarPorId(@PathVariable Long id) {
        Parametro findId = parametroService.listId(id);
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public ResponseEntity<Parametro> criarParametro(@RequestBody ParametroDto dto) {
        Parametro salvo = parametroService.criarParametro(
                dto.getDescricao(),
                dto.getVlmin(),
                dto.getVlmax(),
                dto.getValor(),
                dto.getStatusenum(),
                dto.getGrandezaId(),
                dto.getUnidadeId(),
                dto.getFuncaoenum()
        );
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParametro(@PathVariable Long id) {
        parametroService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parametro> autualizarParametro(@PathVariable Long id,
                                                         @RequestBody ParametroDto parametroDto){
        Parametro atualizado = parametroService.atualizar(id, parametroDto);
        return ResponseEntity.ok(atualizado);
    }

}

//curl -X POST "http://localhost:8080/api/parametro" -H "Content-Type: application/json" --data "{\"valor\":50,\"vlmin\":10,\"vlmax\":100,\"statusenum\":\"ATIVO\",\"descricao\":\"Parâmetro de teste\",\"unidadeId\":1,\"funcaoenum\":\"PRODUCAO\",\"grandezaId\":2}"
//curl -X DELETE http://localhost:8080/api/parametro/1
//curl -X PUT http://localhost:8080/api/parametro/1 \ -H "Content-Type: application/json" \ -d '{"valor":60,"vlmin":15,"vlmax":110,"statusenum":"INATIVO","descricao":"Atualizado","unidadeId":1,"funcaoenum":"SOMA","grandezaId":2}'