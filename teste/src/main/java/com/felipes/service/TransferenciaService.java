package com.felipes.service;

import com.felipes.domain.dto.TransferenciaDTO;
import java.util.List;

public interface TransferenciaService {
  List<TransferenciaDTO> findAll();

  TransferenciaDTO saveTransferencia(TransferenciaDTO transferenciaDTO);

  void deleteTransferencia(Long id);
}
