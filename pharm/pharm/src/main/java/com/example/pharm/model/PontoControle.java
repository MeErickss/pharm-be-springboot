package com.example.pharm.model;

import com.example.pharm.model.enumeration.CLPTipoEnum;
import com.example.pharm.model.enumeration.OffsetEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import com.example.pharm.model.enumeration.TipoUsoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "ponto_controle")
public class PontoControle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pontoControle;

    @Column(nullable = false)
    private CLPTipoEnum clpTipo;

    @Column(nullable = false, unique = true)
    private String enderecoCLP;

    @Column(nullable = false)
    private Short tamanho;

    @Enumerated
    @Column(name = "offset_valor")
    private OffsetEnum offset;

    @Column(nullable = false)
    private String descricao;

    @Enumerated
    private StatusEnum status;

    @Enumerated
    private TipoUsoEnum tipoUso;

    public PontoControle(){}

    public PontoControle(Long id, String pontoControle, CLPTipoEnum clpTipo, String enderecoCLP, Short tamanho, OffsetEnum offset, String descricao, StatusEnum status, TipoUsoEnum tipoUso){
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

    public Long getId() {return id;}
    public String getDescricao() {return descricao;}
    public StatusEnum getStatus() {return status;}
    public CLPTipoEnum getClpTipo() {return clpTipo;}
    public OffsetEnum getOffset() {return offset;}
    public Short getTamanho() {return tamanho;}
    public String getEnderecoCLP() {return enderecoCLP;}
    public String getPontoControle() {return pontoControle;}
    public TipoUsoEnum getTipoUso() {return tipoUso;}

    public void setStatus(StatusEnum status) {this.status = status;}
    public void setClpTipo(CLPTipoEnum clpTipo) {this.clpTipo = clpTipo;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public void setEnderecoCLP(String enderecoCLP) {this.enderecoCLP = enderecoCLP;}
    public void setOffset(OffsetEnum offset) {this.offset = offset;}
    public void setPontoControle(String pontoConrtole) {this.pontoControle = pontoConrtole;}
    public void setTamanho(Short tamanho) {this.tamanho = tamanho;}
    public void setTipoUso(TipoUsoEnum tipoUso) {this.tipoUso = tipoUso;}
}
