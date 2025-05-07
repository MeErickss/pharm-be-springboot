package com.example.pharm.dto;


import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;

public class ParametroDto {
    private Integer valor;
    private Integer vlmin;
    private Integer vlmax;
    private StatusEnum statusenum;
    private String descricao;
    private Long unidadeId;
    private FuncaoEnum funcaoenum;
    private Long grandezaId;

    // Construtor vazio é essencial
    public ParametroDto() {}

    // (Opcional) construtor completo
    public ParametroDto(Integer valor, Integer vlmin, Integer vlmax,
                        StatusEnum statusenum, String descricao,
                        Long unidadeId, FuncaoEnum funcaoenum, Long grandezaId) {
        this.valor = valor;
        this.vlmin = vlmin;
        this.vlmax = vlmax;
        this.statusenum = statusenum;
        this.descricao = descricao;
        this.unidadeId = unidadeId;
        this.funcaoenum = funcaoenum;
        this.grandezaId = grandezaId;
    }

    // Getters e setters para todos os campos
    public Integer getValor() { return valor; }
    public void setValor(Integer valor) { this.valor = valor; }
    public Integer getVlmin() { return vlmin; }
    public void setVlmin(Integer vlmin) { this.vlmin = vlmin; }
    public Integer getVlmax() { return vlmax; }
    public void setVlmax(Integer vlmax) { this.vlmax = vlmax; }
    public StatusEnum getStatusenum() { return statusenum; }
    public void setStatusenum(StatusEnum statusenum) { this.statusenum = statusenum; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Long getUnidadeId() { return unidadeId; }
    public void setUnidadeId(Long unidadeId) { this.unidadeId = unidadeId; }
    public FuncaoEnum getFuncaoenum() { return funcaoenum; }
    public void setFuncaoenum(FuncaoEnum funcaoenum) { this.funcaoenum = funcaoenum; }
    public Long getGrandezaId() { return grandezaId; }
    public void setGrandezaId(Long grandezaId) { this.grandezaId = grandezaId; }
}
