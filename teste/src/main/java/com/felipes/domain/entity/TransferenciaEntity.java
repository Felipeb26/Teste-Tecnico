package com.felipes.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

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

  @Builder.Default
  private Boolean efetuada = false;

  @JoinColumn(name = "CONTADESTINO")
  @ManyToOne(fetch = FetchType.EAGER)
  @ToString.Exclude
  private ContatoEntity contaDestino;
}
