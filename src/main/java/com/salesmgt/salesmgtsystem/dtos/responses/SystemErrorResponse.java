package com.salesmgt.salesmgtsystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class SystemErrorResponse {
    private HttpStatus status;
    private String message;

}
