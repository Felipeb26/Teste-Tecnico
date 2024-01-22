package com.felipes.config.exeception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class BussinesException extends ResponseStatusException {

  private final String message;
  private final Object[] args;
  private StatusError statusEnum;

  public BussinesException(
    HttpStatusCode status,
    String message,
    Object[] args
  ) {
    super(status);
    this.message = message;
    this.args = args;
  }

  public BussinesException(HttpStatusCode status, String message) {
    super(status);
    this.message = message;
    this.args = new Object[] {};
  }

  public BussinesException(
    HttpStatusCode status,
    String message,
    StatusError statusError
  ) {
    super(status);
    this.message = null;
    this.args = new Object[] {};
    this.statusEnum = statusError;
  }
}
