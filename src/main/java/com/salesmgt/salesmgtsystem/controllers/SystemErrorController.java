package com.salesmgt.salesmgtsystem.controllers;

import com.salesmgt.salesmgtsystem.dtos.responses.SystemErrorResponse;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SystemErrorController {
    @ExceptionHandler(SalesMgtException.class)
    public ResponseEntity<SystemErrorResponse> handleException(SalesMgtException e){
    SystemErrorResponse errorResponse = new SystemErrorResponse();
    errorResponse.setStatus(e.getStatus());
    errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }
}
