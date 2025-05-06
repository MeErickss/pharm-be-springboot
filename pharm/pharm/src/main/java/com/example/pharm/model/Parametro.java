package com.example.pharm.model;

import com.example.pharm.model.enumeration.FuncaoEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import jakarta.persistence.*;
import org.hibernate.engine.spi.Status;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parametro")
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    private Integer vlMin;

    @Column(nullable = false)
    private Integer vlMax;

    private StatusEnum status;

    @Column(nullable = false)
    private FuncaoEnum funcaoEnum;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "parametro_grandeza",
            joinColumns = @JoinColumn(name = "id_parametro"),
            inverseJoinColumns = @JoinColumn(name = "id_grandeza")
    )
    private List<Grandeza> grandeza = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "parametros_unidade",
            joinColumns = @JoinColumn(name = "id_parametro"),
            inverseJoinColumns = @JoinColumn(name = "id_unidade")
    )
    private List<Unidade> unidades = new ArrayList<>();

    public Parametro() {} // Construtor vazio

    public Parametro(Long id, String descricao, Integer valor, Integer vlMin, Integer vlMax, StatusEnum status) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.vlMin = vlMin;
        this.vlMax = vlMax;
        this.status = status;
    }

    public Long getId() { return id; }
    public StatusEnum getStatus() { return status; }

    public void setParametro(String descricao) { this.descricao = descricao; }
    public void setValor(Integer valor) { this.valor = valor; }
    public void setVlMin(Integer vlMin) { this.vlMin = vlMin; }
    public void setVlMax(Integer vlMax) { this.vlMax = vlMax; }
    public void setStatus(StatusEnum status) { this.status = status; }

    public FuncaoEnum getFuncoes() { return funcaoEnum; }
    public void setFuncoes(FuncaoEnum funcoes) { this.funcaoEnum = funcoes; }

    public List<Grandeza> getGrandeza() { return grandeza; }
    public void setGrandeza(List<Grandeza> grandeza) { this.grandeza = grandeza; }

    public List<Unidade> getUnidades() { return unidades; }
    public void setUnidades(List<Unidade> unidades) { this.unidades = unidades; }

    public String getParametro() {
        return descricao;
    }
}
