package com.example.pharm.dto;

import com.example.pharm.model.enumeration.CLPTipoEnum;
import com.example.pharm.model.enumeration.OffsetEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoUsoEnum;

public class PontoControleDto {
    private Long id;
    private String pontoControle;
    private CLPTipoEnum clpTipo;
    private String enderecoCLP;
    private Short tamanho;
    private OffsetEnum offset;
    private String descricao;
    private StatusEnum status;
    private TipoUsoEnum tipoUso;

    public PontoControleDto(){}

    public PontoControleDto(Long id, String pontoControle, CLPTipoEnum clpTipo, String enderecoCLP, Short tamanho, OffsetEnum offset, String descricao, StatusEnum status, TipoUsoEnum tipoUso){
        this.id = id;
        this.pontoControle = pontoControle;
        this.clpTipo = clpTipo;
        this.enderecoCLP = enderecoCLP;
        this.tamanho = tamanho;
        this.offset = offset;
        this.descricao = descricao;
        this.status = status;
        this.tipoUso = tipoUso;
    }

    public OffsetEnum getOffset() {return offset;}
    public Short getTamanho() {return tamanho;}
    public CLPTipoEnum getClpTipo() {return clpTipo;}
    public StatusEnum getStatus() {return status;}
    public String getEnderecoCLP() {return enderecoCLP;}
    public String getDescricao() {return descricao;}
    public Long getId() {return id;}
    public String getPontoConteole() {return pontoControle;}
    public TipoUsoEnum getTipoUso() {return tipoUso;}

    public void setEnderecoCLP(String enderecoCLP) {this.enderecoCLP = enderecoCLP;}
    public void setPontoControle(String pontoControle) {this.pontoControle = pontoControle;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public void setOffset(OffsetEnum offset) {this.offset = offset;}
    public void setClpTipo(CLPTipoEnum clpTipo) {this.clpTipo = clpTipo;}
    public void setStatus(StatusEnum status) {this.status = status;}
    public void setTamanho(Short tamanho) {this.tamanho = tamanho;}
    public void setTipoUso(TipoUsoEnum tipoUso) {this.tipoUso = tipoUso;}
}
