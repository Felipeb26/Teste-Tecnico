package com.felipes.teste.config.exeception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusError {

    UNKNOW_ERROR("UNKNOW_ERROR"), DATA_INTEGRITY("DATA_VIOLATION");
    private final String error;
}