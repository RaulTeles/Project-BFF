package com.raulteles.ProjectBFF.adapter.input;

import com.raulteles.ProjectBFF.application.dto.CreateCustomerDTO;
import com.raulteles.ProjectBFF.application.dto.CustomerContactDTO;
import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import com.raulteles.ProjectBFF.application.dto.CustomerDocumentDTO;
import com.raulteles.ProjectBFF.application.port.input.BffInputPort;
import com.raulteles.ProjectBFF.exception.ApiException;
import com.raulteles.ProjectBFF.exception.CpfAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        CustomerDTO customer = new CustomerDTO(1L, "Raul", "Dev", List.of(), List.of());

        Mockito.when(bffInputPort.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Raul"));
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
        CustomerDTO customer = new CustomerDTO(1L, "Raul", "Dev", List.of(), List.of());

        Mockito.when(bffInputPort.getCustomerByName("Raul")).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/name/Raul"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Raul"));
    }

    @Test
    public void testGetCustomerByName_NotFound() throws Exception {

        Mockito.when(bffInputPort.getCustomerByName("Lionel Messi")).thenThrow(new ApiException("Cliente não encontrado"));

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/name/Lionel Messi"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Cliente não encontrado"));
    }

    @Test
    public void testGetCustomerByDocumentNumber_Success() throws Exception {
        CustomerDTO customer = new CustomerDTO(1L, "Raul", "Dev", List.of(), List.of());

        Mockito.when(bffInputPort.getCustomerByDocumentNumber("12345678901")).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/documentNumber?number=12345678901"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Raul"));
    }

    @Test
    public void testGetCustomerByDocumentNumber_NotFound() throws Exception {
        Mockito.when(bffInputPort.getCustomerByDocumentNumber("00000000000")).thenThrow(new ApiException("Documento não encontrado"));

        mockMvc.perform(MockMvcRequestBuilders.get("/bff/cliente/documentNumber?number=00000000000"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Documento não encontrado"));
    }

    @Test
    void testCreateCustomer_Success() throws Exception {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(
                "João Silva",
                1L,
                List.of(new CustomerDocumentDTO("12345678901", "CPF")),
                List.of(new CustomerContactDTO("joao.silva@example.com", "e-mail"))
        );

        CustomerDTO expectedCustomerDTO = new CustomerDTO(
                1L,
                "João Silva",
                "Classic",
                List.of(new CustomerDocumentDTO("12345678901", "CPF")),
                List.of(new CustomerContactDTO("joao.silva@example.com", "e-mail"))
        );

        Mockito.when(bffInputPort.createCustomer(createCustomerDTO)).thenReturn(expectedCustomerDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/bff/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "João Silva",
                                  "segmentId": 1,
                                  "documents": [
                                    {
                                      "documentNumber": "12345678901",
                                      "documentType": "CPF"
                                    }
                                  ],
                                  "contacts": [
                                    {
                                      "contactCustomer": "joao.silva@example.com",
                                      "contactType": "e-mail"
                                    }
                                  ]
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("João Silva"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.segmentName").value("Classic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documents[0].documentNumber").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documents[0].documentType").value("CPF"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contacts[0].contactCustomer").value("joao.silva@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contacts[0].contactType").value("e-mail"));
    }

    @Test
    void testCreateCustomer_CpfAlreadyExists() throws Exception {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(
                "João Silva",
                1L,
                List.of(new CustomerDocumentDTO("12345678901", "CPF")),
                List.of(new CustomerContactDTO("joao.silva@example.com", "e-mail"))
        );

        Mockito.when(bffInputPort.createCustomer(createCustomerDTO))
                .thenThrow(new CpfAlreadyExistsException(HttpStatus.CONFLICT, "CPF já existe"));

        mockMvc.perform(MockMvcRequestBuilders.post("/bff/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "João Silva",
                                  "segmentId": 1,
                                  "documents": [
                                    {
                                      "documentNumber": "12345678901",
                                      "documentType": "CPF"
                                    }
                                  ],
                                  "contacts": [
                                    {
                                      "contactCustomer": "joao.silva@example.com",
                                      "contactType": "e-mail"
                                    }
                                  ]
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("CPF já existe"));
    }
}