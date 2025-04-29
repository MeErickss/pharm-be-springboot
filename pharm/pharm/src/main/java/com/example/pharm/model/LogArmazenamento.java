package com.example.pharm.model;

import jakarta.persistence.*;

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
    private Usuarios user;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String datahora;

    @ManyToOne
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(
            name = "fk_status_logArmazenamento",
            foreignKeyDefinition = "FOREIGN KEY (status_descricao) REFERENCES STATUS(descricao) ON DELETE CASCADE"
    ))
    private Status status;

    public LogArmazenamento() {} // Construtor vazio obrigatório para o JPA

    public LogArmazenamento(Long id, Usuarios user, String descricao, String datahora, Status status) {
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

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
