package com.raulteles.ProjectBFF.application.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDocumentDTOTest {

    @Test
    void testCustomerDocumentDTO() {
        CustomerDocumentDTO dto = new CustomerDocumentDTO("12345678901", "CPF");

        assertEquals("12345678901", dto.documentNumber());
        assertEquals("CPF", dto.documentType());
        assertNotNull(dto.toString());
        CustomerDocumentDTO sameDto = new CustomerDocumentDTO("12345678901", "CPF");
        assertEquals(dto, sameDto);

        assertEquals(dto.hashCode(), sameDto.hashCode());
    }
}