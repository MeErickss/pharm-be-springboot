package com.example.pharm.controller;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.dto.ParametroOutDto;
import com.example.pharm.model.Parametro;
import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.repository.ParametroRepository;
import com.example.pharm.service.ParametroService;
import com.example.pharm.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/parametro")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ParametroController {
    private final ParametroService parametroService;
    private final TokenService tokenService;
    private final ParametroRepository parametroRepository;

    public ParametroController(ParametroService parametroService, TokenService tokenService, ParametroRepository parametroRepository){
        this.parametroService = parametroService;
        this.tokenService = tokenService;
        this.parametroRepository = parametroRepository;
    }

    @GetMapping
    public ResponseEntity<List<ParametroOutDto>> listAll(
            @CookieValue(name = "JWT", required = false) String token,
            @RequestParam FuncaoEnum funcaoEnum) {


        if (token == null || tokenService.validarToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<ParametroOutDto> lista = parametroRepository.findAllOut(funcaoEnum);
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Parametro> buscarPorId(@PathVariable Long id) {
        Parametro findId = parametroService.listId(id);
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public ResponseEntity<ParametroOutDto> inserirParametro(@RequestBody ParametroDto dto) {
        parametroService.insertParametro(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParametro(@PathVariable Long id) {
        parametroService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping
    public ResponseEntity<Parametro> autualizarParametro(@RequestBody ParametroDto parametroDto){
        Parametro atualizado = parametroService.atualizarParametro(parametroDto);
        return ResponseEntity.ok(atualizado);
    }

}

//curl -X POST "http://localhost:8080/api/parametro" -H "Content-Type: application/json" --data "{\"valor\":50,\"vlmin\":10,\"vlmax\":100,\"statusenum\":\"ATIVO\",\"descricao\":\"Parâmetro de teste\",\"unidadeId\":1,\"funcaoenum\":\"PRODUCAO\",\"grandezaId\":2}"
//curl -X DELETE http://localhost:8080/api/parametro/1
//curl -X PUT http://localhost:8080/api/parametro/1 \ -H "Content-Type: application/json" \ -d '{"valor":60,"vlmin":15,"vlmax":110,"statusenum":"INATIVO","descricao":"Atualizado","unidadeId":1,"funcaoenum":"SOMA","grandezaId":2}'