package com.example.pharm.model;

import com.example.pharm.dto.ParametroDto;
import com.example.pharm.dto.ParametroDtoConverter;
import com.example.pharm.model.enumeration.StatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "log_producao")
public class LogProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(
            name = "fk_usuario_logAlarmes",
            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES usuarios(id) ON DELETE CASCADE"
    ))
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "parametro_id", foreignKey = @ForeignKey(
            name = "fk_parametro_logProducao",
            foreignKeyDefinition = "FOREIGN KEY (parametro_id) REFERENCES parametro(id) ON DELETE CASCADE"
    ))
    private Parametro parametro;

    @Convert(converter = ParametroDtoConverter.class)
    @Column(name = "parametro_anterior", columnDefinition = "TEXT")
    private ParametroDto parametroAnterior;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String dataHora;

    @Column(nullable = false)
    private StatusEnum status;

    public LogProducao() {}

    public LogProducao(Long id, Usuario user, String descricao, String dataHora, StatusEnum status, Parametro parametro, ParametroDto parametroAnterior) {
        this.id = id;
        this.user = user;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.status = status;
        this.parametro = parametro;
        this.parametroAnterior = parametroAnterior;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Parametro getParametro() {return parametro;}

    public void setParametro(Parametro parametro) {this.parametro = parametro;}

    public ParametroDto getParametroAnterior() {return parametroAnterior;}

    public void setParametroAnterior(ParametroDto parametroAnterior) {this.parametroAnterior = parametroAnterior;}
}
