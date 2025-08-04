package com.example.pharm.service;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

@Service
public class PdfService {

    private static final DateTimeFormatter FORMATADOR_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Gera um PDF a partir de uma coleção de DTOs.
     * Cada objeto é impresso em sequência, separado por um título ou linha.
     */
    public byte[] gerarPdfDeDtos(Collection<?> dtos, String tabela) {
        StringBuilder sb = new StringBuilder();

        // 1) Linha de data
        String data = LocalDateTime.now().format(FORMATADOR_DATA);
        sb.append("Data: ").append(data).append("\n\n");

        int count = 1;
        for (Object dto : dtos) {
            sb.append("--- "+tabela+" ").append(count++).append(" ---\n");

            if (dto instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String,Object> map = (Map<String,Object>) dto;
                map.forEach((key, value) -> {
                    if (value != null && !"".equals(value)) {
                        sb.append(capitalize(key))
                                .append(": ")
                                .append(value.toString())
                                .append("\n");
                    }
                });
            } else {
                BeanWrapper wrapper = new BeanWrapperImpl(dto);
                for (var pd : wrapper.getPropertyDescriptors()) {
                    String name = pd.getName();
                    if ("class".equals(name)) continue;
                    Object value = wrapper.getPropertyValue(name);
                    if (value != null && !"".equals(value)) {
                        sb.append(capitalize(name))
                                .append(": ")
                                .append(value.toString())
                                .append("\n");
                    }
                }
            }
            sb.append("\n");
        }

        return gerarPdfDeTexto("Relatório de "+tabela, sb.toString().trim());
    }

    private byte[] gerarPdfDeTexto(String titulo, String conteudo) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(titulo).setFontSize(18));
            document.add(new Paragraph(conteudo));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
