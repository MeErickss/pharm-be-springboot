package com.example.pharm.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class ParametroDtoConverter implements AttributeConverter<ParametroDto, String> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ParametroDto attribute) {
        if (attribute == null) return null;
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Erro ao converter ParametroDto para JSON", e);
        }
    }

    @Override
    public ParametroDto convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return mapper.readValue(dbData, ParametroDto.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Erro ao converter JSON para ParametroDto", e);
        }
    }
}
