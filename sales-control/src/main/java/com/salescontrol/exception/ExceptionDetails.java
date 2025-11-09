package com.salescontrol.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime timestamp;
    protected String error;
    protected int status;
    protected String message;
    protected String developMessage;

}