package com.example.pharm.dto;


import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ParametroDto {
    private Long id;
    private Integer valor;
    private Integer vlmin;
    private Integer vlmax;
    private StatusEnum statusenum;
    private String descricao;
    private String unidadeDesc;
    private FuncaoEnum funcao;
    private String grandezaDesc;

    // Construtor vazio é essencial
    public ParametroDto() {}

    // (Opcional) construtor completo
    public ParametroDto(Long id,Integer valor, Integer vlmin, Integer vlmax,
                        StatusEnum statusenum, String descricao,
                        String unidadeDesc, FuncaoEnum funcao, String grandezaDesc) {
        this.valor = valor;
        this.vlmin = vlmin;
        this.vlmax = vlmax;
        this.statusenum = statusenum;
        this.descricao = descricao;
        this.unidadeDesc = unidadeDesc;
        this.funcao = funcao;
        this.grandezaDesc = grandezaDesc;
        this.id = id;
    }

    // Getters e setters para todos os campos

    public Long getId() {return id;}
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
    public String getUnidadeDesc() { return unidadeDesc; }
    public void setUnidadeDesc(String unidadeDesc) { this.unidadeDesc = unidadeDesc; }
    public FuncaoEnum getFuncao() { return funcao; }
    public void setFuncao(FuncaoEnum funcao) { this.funcao = funcao; }
    public String getGrandezaDesc() { return grandezaDesc; }
    public void setGrandezaDesc(String grandezaDesc) { this.grandezaDesc = grandezaDesc; }
}
