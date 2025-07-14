package com.example.pharm.dto;

import com.example.pharm.model.Grandeza;
import com.example.pharm.model.enumeration.StatusEnum;

public class GrandezaDto {

    private Long id;
    private String descricao;
    private StatusEnum status;
    private Long unidadeId;

    public GrandezaDto() {}

    public GrandezaDto(Long id, String descricao, StatusEnum status, Long unidadeId) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.unidadeId = unidadeId;
    }


    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public StatusEnum getStatus() { return status; }
    public Long getUnidadeId() { return unidadeId; }

    public void setId(Long id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setStatus(StatusEnum status) { this.status = status; }
    public void setUnidadeId(Long unidadeId) { this.unidadeId = unidadeId; }
}
