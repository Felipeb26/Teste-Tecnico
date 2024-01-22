package com.felipes.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
public abstract class AbstractEntity<T> {

  @Id
  @Getter(value = AccessLevel.PUBLIC)
  @Setter(value = AccessLevel.PUBLIC)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Getter(value = AccessLevel.PUBLIC)
  @Setter(value = AccessLevel.PUBLIC)
  @CreatedDate
  protected LocalDateTime dataCadastro;

  @PrePersist
  private void saveDataCadastro() {
    this.dataCadastro = LocalDateTime.now();
  }
}
