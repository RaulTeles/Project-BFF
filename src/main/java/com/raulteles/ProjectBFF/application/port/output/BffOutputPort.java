package com.raulteles.ProjectBFF.application.port.output;

import com.raulteles.ProjectBFF.application.dto.CustomerDTO;

public interface BffOutputPort {

    CustomerDTO getCustomerById(Long id);
    CustomerDTO getCustomerByName(String name);

}

