package com.raulteles.ProjectBFF.application.service;

import com.raulteles.ProjectBFF.adapter.output.CustomerApi;
import com.raulteles.ProjectBFF.application.dto.CreateCustomerDTO;
import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import com.raulteles.ProjectBFF.application.port.input.BffInputPort;
import com.raulteles.ProjectBFF.exception.ApiException;
import feign.FeignException;
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
        try {
            return customerApi.getCustomerById(id);
        } catch (FeignException.NotFound e) {
            throw new ApiException("Cliente {" + id + "} não encontrado, verifique o id informado");
        }
    }

    public CustomerDTO getCustomerByName(String name) {
        try {
            return customerApi.getCustomerByName(name);
        } catch (FeignException.NotFound e) {
            throw new ApiException("Cliente {" + name + "} não encontrado, verifique o nome informado");
        }
    }

    public CustomerDTO getCustomerByDocumentNumber(String documentNumber){
        try {
            return customerApi.getCustomerByDocumentNumber(documentNumber);
        } catch (FeignException.NotFound e){
            throw new ApiException("Documento {" + documentNumber + "} não encontrado, verifique o número informado");
        }
    }

    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        return customerApi.createCustomer(createCustomerDTO);
    }

}
