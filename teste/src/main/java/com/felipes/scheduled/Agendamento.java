package com.felipes.scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.felipes.repository.ContatoRepository;
import com.felipes.repository.TransferenciaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class Agendamento {

    private final TransferenciaRepository transferenciaRepository;
    private final ContatoRepository contatoRepository;

    @Async
    @Scheduled(cron = "0 */3 * * * *") // Run at 9:00 every day
    public void transfereAs9() {
        log.info("Iniciando transferencias {}", frindlyDate());
        executeTransferencia();
        log.info("Finalizada Transferencia as {}", frindlyDate());
    }

    @Async
    @Scheduled(cron = "0 0 17 * * *") // Run at 17:00 every day
    public void transfereAs17() {
        log.info("Iniciando transferencias {}", frindlyDate());
        executeTransferencia();
        log.info("Finalizada Transferencia as {}", frindlyDate());
    }

    private void executeTransferencia(){
        var dataInicial = LocalDate.now();
        dataInicial.minusDays(1);

        var transferencias = transferenciaRepository.findAllByDataTransferencia(dataInicial, LocalDate.now());
        log.info("Localizadas {} transferencias", transferencias.size());

        transferencias.stream().forEach(transferencia -> {
            var contatoConta = transferencia.getContaDestino();
            contatoConta.setValor(contatoConta.getValor().add(transferencia.getValor()));
            contatoRepository.save(contatoConta);

            transferencia.setEfetuada(true);
            transferenciaRepository.save(transferencia);

            log.info("Efetuada transferencia para {}", contatoConta.getNome());
        });
    }

    private String frindlyDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.now().format(formatter);
    }
}
