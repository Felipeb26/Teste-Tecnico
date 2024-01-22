package com.felipes.controller;

import com.felipes.domain.dto.TransferenciaDTO;
import com.felipes.service.TransferenciaService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
  value = "/v1/transferencia",
  produces = { MediaType.APPLICATION_JSON_VALUE }
)
@RequiredArgsConstructor
public class TransferenciaController {

  private final TransferenciaService transferenciaService;

  @GetMapping
  public List<TransferenciaDTO> findAll() {
    return transferenciaService.findAll();
  }

  @PostMapping
  public TransferenciaDTO save(
    @RequestBody @Valid TransferenciaDTO transferenciaDTO
  ) {
    return transferenciaService.saveTransferencia(transferenciaDTO);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    transferenciaService.deleteTransferencia(id);
  }
}
