package com.raulteles.ProjectBFF.application.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerContactDTOTest {

    @Test
    void testCustomerContactDTO() {
        CustomerContactDTO dto = new CustomerContactDTO("maria.silva@example.com", "email");

        assertEquals("maria.silva@example.com", dto.contactCustomer());
        assertEquals("email", dto.contactType());

        assertNotNull(dto.toString());

        CustomerContactDTO sameDto = new CustomerContactDTO("maria.silva@example.com", "email");
        assertEquals(dto, sameDto);

        assertEquals(dto.hashCode(), sameDto.hashCode());
    }
}