package com.felipes.service.impl;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.felipes.config.exeception.BussinesException;
import com.felipes.domain.dto.ContatoDTO;
import com.felipes.domain.mapper.ContatoMapper;
import com.felipes.repository.ContatoRepository;
import com.felipes.service.ContatoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContatoServiceImpl implements ContatoService {

  private final ContatoRepository contatoRepository;
  private final ContatoMapper contatoMapper;

  @Override
  public List<ContatoDTO> findAll() {
    try {
      return contatoMapper.entitysToDTOs(contatoRepository.findAll());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new BussinesException(
        BAD_REQUEST,
        e.getMessage(),
        e.getSuppressed()
      );
    }
  }

  @Override
  public ContatoDTO findById(Long id) {
    var entity = contatoRepository
      .findById(id)
      .orElseThrow(() ->
        new BussinesException(BAD_REQUEST, "Contato não encontrado!")
      );
    return contatoMapper.entityToDTO(entity);
  }

  @Override
  public ContatoDTO save(ContatoDTO contatoDTO) {
    try {
      var contato = contatoRepository.save(
        contatoMapper.dtoToEntity(contatoDTO)
      );
      return contatoMapper.entityToDTO(contato);
    } catch (DataIntegrityViolationException violationException) {
      throw violationException;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new BussinesException(
        BAD_REQUEST,
        e.getMessage(),
        e.getSuppressed()
      );
    }
  }

  @Override
  public void delete(Long id) {
    try {
      contatoRepository.deleteById(id);
    } catch (Exception e) {
      throw new BussinesException(
        BAD_REQUEST,
        e.getMessage(),
        e.getSuppressed()
      );
    }
  }

  @Override
  public ContatoDTO update(Long id, ContatoDTO contatoDTO) {
    try {
      contatoRepository
        .findById(id)
        .orElseThrow(() ->
          new BussinesException(BAD_REQUEST, "Contato não encontrado!")
        );

      var contato = contatoMapper.dtoToEntity(contatoDTO);
      contato.setId(contatoDTO.id());
      contato = contatoRepository.save(contato);

      return contatoMapper.entityToDTO(contato);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new BussinesException(
        BAD_REQUEST,
        e.getMessage(),
        e.getSuppressed()
      );
    }
  }
}
