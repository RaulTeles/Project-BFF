package com.raulteles.ProjectBFF.application.service;

import com.raulteles.ProjectBFF.adapter.output.CustomerApi;
import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import com.raulteles.ProjectBFF.exception.ApiException;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BffServiceTest {

    @Mock
    private CustomerApi customerApi;

    @InjectMocks
    private BffService bffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerById_Success() {
        Long id = 1L;
        CustomerDTO mockCustomer = new CustomerDTO(id, "Raul", "Dev", List.of(), List.of());

        when(customerApi.getCustomerById(id)).thenReturn(mockCustomer);

        CustomerDTO result = bffService.getCustomerById(id);

        assertNotNull(result);
        assertEquals(mockCustomer, result);

        verify(customerApi, times(1)).getCustomerById(id);
    }

    @Test
    void testGetCustomerById_NotFound() {
        Long id = 999L;

        when(customerApi.getCustomerById(id)).thenThrow(FeignException.NotFound.class);

        ApiException exception = assertThrows(ApiException.class, () -> {
            bffService.getCustomerById(id);
        });

        assertEquals("Cliente {999} não encontrado, verifique o id informado", exception.getMessage());

        verify(customerApi, times(1)).getCustomerById(id);
    }

    @Test
    void testGetCustomerByName_Success() {
        String name = "Raul";
        CustomerDTO mockCustomer = new CustomerDTO(1L, name, "Dev", List.of(), List.of());

        when(customerApi.getCustomerByName(name)).thenReturn(mockCustomer);

        CustomerDTO result = bffService.getCustomerByName(name);

        assertNotNull(result);
        assertEquals(mockCustomer, result);

        verify(customerApi, times(1)).getCustomerByName(name);
    }

    @Test
    void testGetCustomerByName_NotFound() {
        String name = "Lionel Messi";

        when(customerApi.getCustomerByName(name)).thenThrow(FeignException.NotFound.class);

        ApiException exception = assertThrows(ApiException.class, () -> {
            bffService.getCustomerByName(name);
        });

        assertEquals("Cliente {Lionel Messi} não encontrado, verifique o nome informado", exception.getMessage());

        verify(customerApi, times(1)).getCustomerByName(name);
    }
}