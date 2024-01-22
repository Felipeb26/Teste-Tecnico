package com.felipes.domain.mapper;

import com.felipes.domain.dto.ContatoDTO;
import com.felipes.domain.entity.ContatoEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContatoMapper {

  public List<ContatoEntity> dtosToEntitys(List<ContatoDTO> contatoDTOS) {
    return contatoDTOS.stream().map(this::dtoToEntity).toList();
  }

  public List<ContatoDTO> entitysToDTOs(List<ContatoEntity> contatos) {
    return contatos.stream().map(this::entityToDTO).toList();
  }

  public ContatoDTO entityToDTO(ContatoEntity contato) {
    return new ContatoDTO(
      contato.getId(),
      contato.getNome(),
      contato.getConta(),
      contato.getAgencia(),
      contato.getBanco(),
      contato.getValor()
    );
  }

  public ContatoEntity dtoToEntity(ContatoDTO contatoDTO) {
    return ContatoEntity
      .builder()
      .nome(contatoDTO.nome())
      .banco(contatoDTO.banco())
      .agencia(contatoDTO.agencia())
      .conta(contatoDTO.conta())
      .valor(contatoDTO.valor())
      .build();
  }
}
