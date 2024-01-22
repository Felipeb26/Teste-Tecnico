package com.felipes.service.impl;

import com.felipes.domain.dto.TransferenciaDTO;
import com.felipes.domain.mapper.TransferenciaMapper;
import com.felipes.repository.TransferenciaRepository;
import com.felipes.service.TransferenciaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferenciaServiceImpl implements TransferenciaService {

  private final TransferenciaRepository transferenciaRepository;
  private final TransferenciaMapper transferenciaMapper;

  @Override
  public List<TransferenciaDTO> findAll() {
    var transferencias = transferenciaRepository.findAll();
    return transferencias
      .stream()
      .map(transferenciaMapper::entityToDTO)
      .toList();
  }

  @Override
  public TransferenciaDTO saveTransferencia(TransferenciaDTO transferenciaDTO) {
    var entity = transferenciaRepository.save(transferenciaMapper.dtoToEntity(transferenciaDTO));
    return transferenciaMapper.entityToDTO(entity);
  }

  @Override
  public void deleteTransferencia(Long id) {
    transferenciaRepository.deleteById(id);
  }
}
