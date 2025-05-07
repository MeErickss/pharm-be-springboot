package com.example.pharm.dto;

import com.example.pharm.model.LogProducao;
import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.StatusEnum;

public class LogProducaoDto {

    private Long id;
    private Usuario user;
    private String descricao;
    private String dataHora;
    private StatusEnum status;

    public LogProducaoDto(Long id, Usuario user, String descricao, String dataHora, StatusEnum status) {
        this.id = id;
        this.user = user;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.status = status;
    }
}
