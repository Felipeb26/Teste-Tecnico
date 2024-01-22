package com.felipes.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransferenciaDTO(
  ContatoDTO contaDestino,
  String contaEnvio,
  String contatoNome,
  String pessoaEnvio,
  BigDecimal valor,

  @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dataTransferencia,
  LocalDateTime dataAgendamento
) {}
