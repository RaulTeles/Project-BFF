package com.raulteles.ProjectBFF.application.port.input;

import com.raulteles.ProjectBFF.application.dto.CreateCustomerDTO;
import com.raulteles.ProjectBFF.application.dto.CustomerDTO;

public interface BffInputPort {

    CustomerDTO getCustomerById(Long id);
    CustomerDTO getCustomerByName(String name);
    CustomerDTO getCustomerByDocumentNumber(String documentNumber);
    CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO);
}
