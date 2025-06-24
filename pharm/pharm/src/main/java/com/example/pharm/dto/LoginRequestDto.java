package com.example.pharm.dto;

public class LoginRequestDto {
    private String login;
    private String senha;

    // Construtor vazio é obrigatório para Jackson
    public LoginRequestDto() {}

    // Getters e setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
