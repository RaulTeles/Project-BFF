package com.raulteles.ProjectBFF.application.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerDTOTest {

    @Test
    void testCreateCustomerDTO() {
        CreateCustomerDTO dto = new CreateCustomerDTO(
                "João Silva",
                1L,
                List.of(new CustomerDocumentDTO("12345678901", "CPF")),
                List.of(new CustomerContactDTO("joao.silva@example.com", "e-mail"))
        );

        assertEquals("João Silva", dto.name());
        assertEquals(1L, dto.segmentId());
        assertEquals("12345678901", dto.documents().get(0).documentNumber());
        assertEquals("CPF", dto.documents().get(0).documentType());
        assertEquals("joao.silva@example.com", dto.contacts().get(0).contactCustomer());
        assertEquals("e-mail", dto.contacts().get(0).contactType());

        assertNotNull(dto.toString());

        CreateCustomerDTO sameDto = new CreateCustomerDTO(
                "João Silva",
                1L,
                List.of(new CustomerDocumentDTO("12345678901", "CPF")),
                List.of(new CustomerContactDTO("joao.silva@example.com", "e-mail"))
        );
        assertEquals(dto, sameDto);

        assertEquals(dto.hashCode(), sameDto.hashCode());
    }

}