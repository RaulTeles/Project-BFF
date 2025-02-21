package com.raulteles.ProjectBFF.application.service;

import com.raulteles.ProjectBFF.adapter.output.CustomerApi;
import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import com.raulteles.ProjectBFF.application.port.input.BffInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BffService implements BffInputPort {

    private final CustomerApi customerApi;

    @Autowired
    public BffService(CustomerApi customerApi) {
        this.customerApi = customerApi;
    }

    public CustomerDTO getCustomerById(Long id) {
        return customerApi.getCustomerById(id);
    }

    public CustomerDTO getCustomerByName(String name) {
        return customerApi.getCustomerByName(name);
    }
}
