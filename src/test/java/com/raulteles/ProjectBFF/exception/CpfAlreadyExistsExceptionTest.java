package com.raulteles.ProjectBFF.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class CpfAlreadyExistsExceptionTest {

    @Test
    void testCpfAlreadyExistsException() {
        CpfAlreadyExistsException exception = new CpfAlreadyExistsException(HttpStatus.CONFLICT, "CPF já existe");

        assertEquals("CPF já existe", exception.getMessage());

        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
    }
}
