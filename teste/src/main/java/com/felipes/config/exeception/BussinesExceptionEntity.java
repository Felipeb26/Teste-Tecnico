package com.felipes.teste.config.exeception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BussinesExceptionEntity {

    private String path;
    private String time;
    private String error;
    private Object[] arguments;
    private StatusError status;

    public void setTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.time = time.isBlank() ? LocalDateTime.now().format(formatter) : time;
    }

    public void setTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.time = time.isBlank() ? LocalDateTime.now().format(formatter) : time;
    }


}
