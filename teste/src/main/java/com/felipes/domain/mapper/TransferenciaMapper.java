package com.felipes.domain.mapper;

import java.util.List;
import org.springframework.stereotype.Service;
import com.felipes.domain.dto.TransferenciaDTO;
import com.felipes.domain.entity.TransferenciaEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferenciaMapper {

  private final ContatoMapper contatoMapper;


  public List<TransferenciaEntity> dtosToEntitys(List<TransferenciaDTO> transferencias) {
    return transferencias.stream().map(this::dtoToEntity).toList();
  }

  public List<TransferenciaDTO> entitysToDTOs(List<TransferenciaEntity> transferencias) {
    return transferencias.stream().map(this::entityToDTO).toList();
  }

  public TransferenciaEntity dtoToEntity(TransferenciaDTO transferenciaDTO) {
    var contaDTO = transferenciaDTO.contaDestino();
    var conta = contatoMapper.dtoToEntity(contaDTO);
    conta.setId(contaDTO.id());

    return TransferenciaEntity
      .builder()
      .contaDestino(conta)
      .contaEnvio(transferenciaDTO.contaEnvio())
      .contatoNome(transferenciaDTO.contatoNome())
      .pessoaEnvio(transferenciaDTO.pessoaEnvio())
      .valor(transferenciaDTO.valor())
      .dataTransferencia(transferenciaDTO.dataTransferencia())
      .build();
  }

  public TransferenciaDTO entityToDTO(TransferenciaEntity entity) {
    return new TransferenciaDTO(
      contatoMapper.entityToDTO(entity.getContaDestino()),
      entity.getContaEnvio(),
      entity.getContatoNome(),
      entity.getPessoaEnvio(),
      entity.getValor(),
      entity.getDataTransferencia(),
      entity.getDataCadastro()
    );
  }
}
