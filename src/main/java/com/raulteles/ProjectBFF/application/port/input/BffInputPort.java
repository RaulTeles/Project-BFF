package com.raulteles.ProjectBFF.application.port.input;

import com.raulteles.ProjectBFF.application.dto.CustomerDTO;

public interface BffInputPort {

    CustomerDTO getCustomerById(Long id);
    CustomerDTO getCustomerByName(String name);

}
