package com.example.pharm.dto;

import com.example.pharm.model.enumeration.StatusEnum;

public class UnidadeDto {
    private Long id;
    private String unidade;
    private String abreviacao;
    private StatusEnum status;
    private String grandeza;

    // <— Construtor vazio obrigatório
    public UnidadeDto() {}

    // (opcional) construtor completo para uso interno
    public UnidadeDto(Long id, String unidade, String abreviacao,
                      StatusEnum status, String grandeza) {
        this.id = id;
        this.unidade = unidade;
        this.abreviacao = abreviacao;
        this.status = status;
        this.grandeza = grandeza;
    }

    // getters
    public Long getId()              { return id; }
    public String getUnidade()       { return unidade; }
    public String getAbreviacao()    { return abreviacao; }
    public StatusEnum getStatus()    { return status; }
    public String getGrandeza()  { return grandeza; }

    // setters — **importante** para o Spring Data bind
    public void setId(Long id)                       { this.id = id; }
    public void setUnidade(String unidade)            { this.unidade = unidade; }
    public void setAbreviacao(String abreviacao)      { this.abreviacao = abreviacao; }
    public void setStatus(StatusEnum status)          { this.status = status; }
    public void setGrandeza(String grandeza)  { this.grandeza = grandeza; }
}
