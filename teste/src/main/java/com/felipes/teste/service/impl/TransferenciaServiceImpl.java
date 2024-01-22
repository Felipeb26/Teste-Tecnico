package com.felipes.teste.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.felipes.teste.domain.dto.TransferenciaDTO;
import com.felipes.teste.domain.mapper.TransferenciaMapper;
import com.felipes.teste.repository.TransferenciaRepository;
import com.felipes.teste.service.TransferenciaService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final TransferenciaMapper transferenciaMapper;

    @Override
    public List<TransferenciaDTO> findAll() {
        var transferencias = transferenciaRepository.findAll();
        return transferencias.stream().map(transferenciaMapper::entityToDTO).toList();
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
