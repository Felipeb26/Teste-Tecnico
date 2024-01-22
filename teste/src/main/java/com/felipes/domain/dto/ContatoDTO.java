package com.felipes.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ContatoDTO(
  Long id,
  @NotNull @NotEmpty String nome,
  @NotNull Long conta,
  @NotNull Long agencia,
  @NotNull @NotEmpty String banco,
  BigDecimal valor
) {}
