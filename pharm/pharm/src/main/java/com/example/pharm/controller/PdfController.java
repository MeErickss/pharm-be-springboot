package com.example.pharm.controller;

import com.example.pharm.service.PdfService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PdfService pdfService;
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    /**
     * Recebe **um único** DTO em JSON (qualquer formato),
     * mapeado para um Map<String,Object>, e devolve um PDF.
     */
    @PostMapping(
            value = "/dto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<byte[]> gerarPdf(@RequestBody List<Map<String,Object>> dtos,
                                           @RequestParam String tabela) {
        byte[] pdfBytes = pdfService.gerarPdfDeDtos(dtos, tabela);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"relatorio.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
