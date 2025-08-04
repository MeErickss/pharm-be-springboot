package com.example.pharm.controller;

import com.example.pharm.dto.ModbusConectarDto;
import com.example.pharm.dto.LerRequisicaoDto;
import com.example.pharm.dto.LerRespostaDto;
import com.example.pharm.dto.EscreverRequisicaoDto;
import com.example.pharm.service.ModbusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modbus")
public class ModbusController {


    @Autowired
    private ModbusService modbusService;

    @PostMapping("/connect")
    public ResponseEntity<?> connect(@RequestBody ModbusConectarDto req) {
        try {
            modbusService.connect(req.host, req.port);
            return ResponseEntity.ok().body("{\"ok\":true}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/read")
    public ResponseEntity<?> read(@RequestBody LerRequisicaoDto req) {
        try {
            int[] data = modbusService.read(req.type, req.slaveId, req.address, req.length);
            return ResponseEntity.ok().body(new LerRespostaDto(data));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + iae.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody EscreverRequisicaoDto req) {
        try {
            modbusService.write(req.type, req.slaveId, req.address, req.value);
            return ResponseEntity.ok().body("{\"ok\":true}");
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + iae.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/close")
    public ResponseEntity<?> close() {
        modbusService.close();
        return ResponseEntity.ok().body("{\"ok\":true}");
    }

}

