package com.example.pharm.dto;

import com.example.pharm.model.Parametro;

public class ParametroDto {

        private Long id;
        private String descricao;
        public ParametroDto(Parametro parametro) {
            this.id = parametro.getId();
            this.descricao = parametro.getDescricao();
        }
        public String getParametro() { return descricao; }
        public void setParametro(String descricao) { this.descricao = descricao; }
}
