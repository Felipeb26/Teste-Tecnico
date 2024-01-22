package com.felipes.teste.controller;

import com.felipes.teste.domain.dto.TransferenciaDTO;
import com.felipes.teste.service.TransferenciaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/transferencia")
@RequiredArgsConstructor
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @GetMapping
    public List<TransferenciaDTO> findAll() {
        return transferenciaService.findAll();
    }

    @PostMapping
    public TransferenciaDTO save(@RequestBody @Valid TransferenciaDTO transferenciaDTO) {
        return transferenciaService.saveTransferencia(transferenciaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transferenciaService.deleteTransferencia(id);
    }
}
