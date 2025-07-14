package com.example.pharm.dto;

import com.example.pharm.model.Usuario;
import com.example.pharm.model.enumeration.NivelEnum;
import com.example.pharm.model.enumeration.StatusEnum;

public class UsuarioDto {

    private Long id;
    private String login;
    private String password;
    private StatusEnum status;
    private NivelEnum nivel;

    public UsuarioDto() {}

    public UsuarioDto(Long id, String login, String password, StatusEnum status, NivelEnum nivel) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
        this.nivel = nivel;
    }

    /**
     * Converte uma entidade Usuario para o DTO
     */
    public static UsuarioDto fromEntity(Usuario entity) {
        if (entity == null) {
            return null;
        }
        UsuarioDto dto = new UsuarioDto();
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setNivel(entity.getNivel());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public StatusEnum getStatus() { return status; }
    public void setStatus(StatusEnum status) { this.status = status; }
    public NivelEnum getNivel() { return nivel; }
    public void setNivel(NivelEnum nivel) { this.nivel = nivel; }
}
