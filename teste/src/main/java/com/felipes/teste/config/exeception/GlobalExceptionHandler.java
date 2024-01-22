package com.felipes.teste.config.exeception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private String dataAtual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss");
        return LocalDateTime.now().format(formatter);
    }

    private String validUnique(String messageError) {
        if (messageError.contains("Unique index or primary key violation")) {
            return "A mesma conta já está cadastrada";
        }
        return messageError;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<BussinesExceptionEntity> handleDataIntegrity(HttpServletRequest request, DataIntegrityViolationException violationException) {
        var bussiness = BussinesExceptionEntity.builder()
                .path(request.getServletPath())
                .arguments(violationException.getSuppressed())
                .error(validUnique(violationException.getMessage()))
                .status(StatusError.DATA_INTEGRITY)
                .time(dataAtual())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(bussiness);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<BussinesExceptionEntity> handleDataIntegrity(HttpServletRequest request, ConstraintViolationException violationException) {

        log.info(violationException.getConstraintName());

        var bussiness = BussinesExceptionEntity.builder()
                .path(request.getServletPath())
                .arguments(violationException.getSuppressed())
                .error(violationException.getMessage())
                .status(StatusError.DATA_INTEGRITY)
                .time(dataAtual())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bussiness);
    }

    @ExceptionHandler(BussinesException.class)
    private ResponseEntity<Object> response(BussinesException bussinesException, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(BussinesExceptionEntity.builder()
                .error(bussinesException.getMessage())
                .status(isNull(bussinesException.getStatusEnum()) ? StatusError.UNKNOW_ERROR : bussinesException.getStatusEnum())
                .arguments(bussinesException.getArgs())
                .time(dataAtual())
                .path(httpServletRequest.getServletPath())
                .build(), bussinesException.getStatusCode());
    }
}
