package com.example.pharm.model;

import jakarta.persistence.*;
@Entity
@Table(name = "usuarios")
public class Usuarios {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String login;

        @Column(nullable = false, unique = true)
        private String password;


        @ManyToOne
        @JoinColumn(name = "nivel_id", foreignKey = @ForeignKey(
                name = "fk_nivel_usuarios",
                foreignKeyDefinition = "FOREIGN KEY (nivel_id) REFERENCES NIVEL(id) ON DELETE CASCADE"
        ))
        private Niveis nivel;

        @ManyToOne
        @JoinColumn(name = "status_id", foreignKey = @ForeignKey(
                name = "fk_status_usuarios",
                foreignKeyDefinition = "FOREIGN KEY (status_descricao) REFERENCES STATUS(descricao) ON DELETE CASCADE"
        ))
        private Status status;

        // Construtores
        public Usuarios() {}

        public Usuarios(Long id, String login, String password, Status status, Niveis nivel) {
            this.id = id;
            this.login = login;
            this.password = password;
            this.status = status;
            this.nivel = nivel;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Niveis getNivel() {
        return nivel;
    }

    public void setNivel(Niveis nivel) {
        this.nivel = nivel;
    }
}

