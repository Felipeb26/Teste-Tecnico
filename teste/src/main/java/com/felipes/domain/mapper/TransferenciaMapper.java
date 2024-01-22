package com.felipes.teste.domain.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.felipes.teste.domain.dto.TransferenciaDTO;
import com.felipes.teste.domain.entity.TransferenciaEntity;
import com.felipes.teste.repository.ContatoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferenciaMapper {

    private final ContatoMapper contatoMapper;
    private final ContatoRepository contatoRepository;

    public List<TransferenciaEntity> dtosToEntitys(List<TransferenciaDTO> transferencias) {
        return transferencias.stream().map(this::dtoToEntity).toList();
    }

    public List<TransferenciaDTO> entitysToDTOs(List<TransferenciaEntity> transferencias) {
        return transferencias.stream().map(this::entityToDTO).toList();
    }

    public TransferenciaEntity dtoToEntity(TransferenciaDTO transferenciaDTO) {
        var conta = contatoRepository.findById(transferenciaDTO.contaDestino()).get();
        
        return TransferenciaEntity.builder()
                .contaDestino(conta)
                .contaEnvio(transferenciaDTO.contaEnvio())
                .contatoNome(transferenciaDTO.contatoNome())
                .pessoaEnvio(transferenciaDTO.pessoaEnvio())
                .valor(transferenciaDTO.valor())
                .dataTransferencia(transferenciaDTO.dataTransferencia())
                .build();
    }

    public TransferenciaDTO entityToDTO(TransferenciaEntity entity) {
        return new TransferenciaDTO(contatoMapper.entityToDTO(entity.getContaDestino()).id(),
                entity.getContaEnvio(), entity.getContatoNome(),
                entity.getPessoaEnvio(), entity.getValor(),
                entity.getDataTransferencia(), entity.getDataCadastro());
    }

}
