package com.felipes.teste;

import com.felipes.repository.TransferenciaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class TesteApplicationTests {

  @Autowired
  private TransferenciaRepository repository;

  @Test
  void contextLoads() {}

  @Test
  @DisplayName("Scheduler Test")
  void testScheduler() {
    log.info("Iniciando transferencias {}", LocalDateTime.now());
    var dataInicial = LocalDate.now();
    dataInicial.minusDays(1);

    var transferencias = repository.findAllByDataTransferencia(
      dataInicial,
      LocalDate.now()
    );
    log.info("Localizadas {} transferencias", transferencias);

    log.info("worked");
  }
}
