package com.example.pharm.dto;


import com.example.pharm.model.Parametro;
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
                        String unidadeDesc, FuncaoEnum funcao) {
        this.valor = valor;
        this.vlmin = vlmin;
        this.vlmax = vlmax;
        this.statusenum = statusenum;
        this.descricao = descricao;
        this.unidadeDesc = unidadeDesc;
        this.funcao = funcao;
        this.id = id;
    }

    public static ParametroDto fromEntity(Parametro p) {
        if (p == null) return null;
        ParametroDto dto = new ParametroDto();
        dto.setId(p.getId());
        dto.setValor(p.getValor());
        dto.setVlmin(p.getVlMin());
        dto.setVlmax(p.getVlMax());
        dto.setStatusenum(p.getStatus());
        dto.setDescricao(p.getDescricao());
        dto.setUnidadeDesc(p.getUnidade() != null ? String.valueOf(p.getUnidade().getUnidade()) : null);
        dto.setFuncao(p.getFuncao());
        dto.setGrandezaDesc(p.getGrandeza() != null ? p.getGrandeza().getDescricao() : null);
        return dto;
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
    public void setId(Long id) {this.id = id;}
}
