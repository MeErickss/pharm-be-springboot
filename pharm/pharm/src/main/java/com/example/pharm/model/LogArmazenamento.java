package com.example.pharm.model;

import com.example.pharm.model.enumeration.StatusEnum;
import jakarta.persistence.*;
import org.hibernate.engine.spi.Status;

@Entity
@Table(name = "log_armazenamento")
public class LogArmazenamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(
            name = "fk_usuario_logAlarmes",
            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES usuarios(id) ON DELETE CASCADE"
    ))
    private Usuario user;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String datahora;

    @Column(nullable = false)
    private StatusEnum status;

    public LogArmazenamento() {} // Construtor vazio obrigatório para o JPA

    public LogArmazenamento(Long id, Usuario user, String descricao, String datahora, StatusEnum status) {
        this.id = id;
        this.user = user;
        this.descricao = descricao;
        this.datahora = datahora;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
