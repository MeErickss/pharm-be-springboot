package com.example.pharm.model;

import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;
import jakarta.persistence.*;
import org.hibernate.engine.spi.Status;

@Entity
@Table(name = "usuarios")
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String login;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private NivelEnum nivel;


        @Column(nullable = false)
        private StatusEnum status;

        // Construtores
        public Usuario() {}

        public Usuario(Long id, String login, String password, StatusEnum status, NivelEnum nivel) {
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
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

    public NivelEnum getNivel() {
        return nivel;
    }

    public void setNivel(NivelEnum nivelEnum) {
        this.nivel = nivelEnum;
    }
}

