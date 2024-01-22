package com.felipes.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.felipes.domain.entity.TransferenciaEntity;

public interface TransferenciaRepository  extends JpaRepository<TransferenciaEntity, Long> {


    @Query("FROM TransferenciaEntity te WHERE te.dataTransferencia between :dataInicial and :dataFinal AND te.efetuada=false")
    List<TransferenciaEntity> findAllByDataTransferencia(LocalDate dataInicial, LocalDate dataFinal);

}
