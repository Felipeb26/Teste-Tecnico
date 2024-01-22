package com.felipes.teste.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TRANSFERENCIA_TB")
@DynamicUpdate
@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaEntity extends AbstractEntity<TransferenciaEntity> {

  private String contaEnvio;
  private String contatoNome;
  private String pessoaEnvio;
  private BigDecimal valor;
  private LocalDate dataTransferencia;
  @JoinColumn(name = "CONTADESTINO")
  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  private ContatoEntity contaDestino;
}
