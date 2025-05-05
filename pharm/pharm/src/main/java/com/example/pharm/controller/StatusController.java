package com.example.pharm.controller;

import com.example.pharm.dto.StatusDto;
import com.example.pharm.model.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService, StatusRepository statusRepository) {
        this.statusService = statusService;
    }


    /**
     * Lista todos os status.
     * GET http://localhost:8080/api/status
     */
    @GetMapping
    public StatusDto listAll() {
        List<StatusDto> todos = statusService.listAll();
        return ResponseEntity.ok(todos);
    }

}
