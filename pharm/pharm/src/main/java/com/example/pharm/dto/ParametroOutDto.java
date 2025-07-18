// src/main/java/com/example/pharm/dto/ParametroOutDto.java
package com.example.pharm.dto;

import com.example.pharm.model.enumeration.FormulaEnum;
import com.example.pharm.model.enumeration.StatusEnum;

public class ParametroOutDto {
    private Long   id;
    private String descricao;
    private Integer valor;
    private Integer vlMin;
    private Integer vlMax;
    private StatusEnum status;
    private String unidade;
    private String grandeza;
    private FormulaEnum formulaEnum;
    private String pontoControle;

    public ParametroOutDto(
            Long id,
            String descricao,
            Integer valor,
            Integer vlMin,
            Integer vlMax,
            StatusEnum status,
            String unidade,
            String grandeza,
            FormulaEnum formulaEnum,
            String pontoControle
    ) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.vlMin = vlMin;
        this.vlMax = vlMax;
        this.status = status;
        this.unidade = unidade;
        this.grandeza = grandeza;
        this.formulaEnum = formulaEnum;
        this.pontoControle = pontoControle;
    }

    public Long getId()               { return id; }
    public String getDescricao()      { return descricao; }
    public Integer getValor()         { return valor; }
    public Integer getVlMin()         { return vlMin; }
    public Integer getVlMax()         { return vlMax; }
    public StatusEnum getStatus()     { return status; }
    public String getUnidade()        { return unidade; }
    public String getGrandeza()       { return grandeza; }
    public FormulaEnum getFormula() {return formulaEnum;}
    public String getPontoControle() {return pontoControle;}
}
