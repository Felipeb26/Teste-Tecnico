package com.felipes.teste.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "CONTATO_TB")
@DynamicUpdate
@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContatoEntity extends AbstractEntity<ContatoEntity> {

    private String nome;
    @Column(unique = true)
    private Long conta;
    private Long agencia;
    private String banco;
    private BigDecimal valor;
}
