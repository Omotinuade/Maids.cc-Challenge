package com.salesmgt.salesmgtsystem.exceptions;

import org.springframework.http.HttpStatus;

public class SalesMgtException extends Exception {
        private HttpStatus status;
        public SalesMgtException(HttpStatus status, String message) {
            super(message);
            this.status = status;
        }
        public HttpStatus getStatus() {
            return status;
        }
    }

