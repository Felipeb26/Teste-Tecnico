package com.felipes.teste.service;

import com.felipes.teste.domain.dto.ContatoDTO;

import java.util.List;

public interface ContatoService {
    List<ContatoDTO> findAll();

    ContatoDTO save(ContatoDTO contatoDTO);

    void delete(Long id);

    ContatoDTO update(Long id, ContatoDTO contatoDTO);

    ContatoDTO findById(Long id);
}
