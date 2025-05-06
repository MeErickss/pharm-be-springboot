package com.example.pharm.dto;

import com.example.pharm.model.Parametro;

public class ParametrosDto {

        private Long id;
        private String parametro;
        public ParametrosDto(Parametro parametro) {
            this.id = parametro.getId();
            this.parametro = parametro.getParametro();
        }
        public String getParametro() { return parametro; }
        public void setParametro(String descricao) { this.parametro = descricao; }
}
