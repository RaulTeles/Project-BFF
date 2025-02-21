package com.raulteles.ProjectBFF.adapter.input;

import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import com.raulteles.ProjectBFF.application.port.input.BffInputPort;
import com.raulteles.ProjectBFF.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(BffController.class)
@ActiveProfiles("test")
public class BffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BffInputPort bffInputPort;

    @Test
    public void testGetCustomerById_Success() throws Exception {
        CustomerDTO customer = new CustomerDTO(1L, "João", "Segmento A", List.of(), List.of());

        Mockito.when(bffInputPort.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("João"));
    }

    @Test
    public void testGetCustomerById_NotFound() throws Exception {
        Mockito.when(bffInputPort.getCustomerById(999L)).thenThrow(new ApiException("Cliente não encontrado"));

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Cliente não encontrado"));
    }

    @Test
    public void testGetCustomerByName_Success() throws Exception {
        CustomerDTO customer = new CustomerDTO(1L, "João", "Segmento A", List.of(), List.of());

        Mockito.when(bffInputPort.getCustomerByName("João")).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/name/João"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("João"));
    }

    @Test
    public void testGetCustomerByName_NotFound() throws Exception {

        Mockito.when(bffInputPort.getCustomerByName("Lionel Messi")).thenThrow(new ApiException("Cliente não encontrado"));

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/name/Lionel Messi"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Cliente não encontrado"));
    }
}