package com.felipes.teste.service;

import com.felipes.teste.domain.dto.TransferenciaDTO;

import java.util.List;

public interface TransferenciaService {
    List<TransferenciaDTO> findAll();

    TransferenciaDTO saveTransferencia(TransferenciaDTO transferenciaDTO);

    void deleteTransferencia(Long id);
}
