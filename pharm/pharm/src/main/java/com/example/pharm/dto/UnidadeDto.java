package com.example.pharm.dto;

import com.example.pharm.model.Unidade;
import com.example.pharm.model.enumeration.StatusEnum;
import org.hibernate.sql.ast.tree.from.TableReference;

public class UnidadeDto {

    private Long id;
    private String descricao;
    private String abreviacao;
    private StatusEnum status;

    public UnidadeDto(Long id, String descricao, String abreviacao, StatusEnum status){
        this.id = id;
        this.descricao = descricao;
        this.abreviacao = abreviacao;
        this.status = status;
    }

    public StatusEnum getStatus() {return status;}

    public String getDescricao() {return descricao;}

    public String getAbreviacao() {return abreviacao;}
}
