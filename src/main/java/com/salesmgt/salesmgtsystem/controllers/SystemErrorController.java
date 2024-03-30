package com.salesmgt.salesmgtsystem.controllers;

import com.salesmgt.salesmgtsystem.dtos.responses.SystemErrorResponse;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemErrorController {
    private Logger logger = LoggerFactory.getLogger(SystemErrorController.class);
    @ExceptionHandler(SalesMgtException.class)
    public ResponseEntity<SystemErrorResponse> handleException(SalesMgtException e){
    logger.error("Error: {}", e.getMessage());
    SystemErrorResponse errorResponse = new SystemErrorResponse();
    errorResponse.setStatus(e.getStatus());
    errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }
}
