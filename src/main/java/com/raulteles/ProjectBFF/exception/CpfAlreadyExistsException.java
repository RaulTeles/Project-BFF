package com.raulteles.ProjectBFF.exception;

import org.springframework.http.HttpStatus;

public class CpfAlreadyExistsException extends RuntimeException {
    private final HttpStatus status;

    public CpfAlreadyExistsException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
