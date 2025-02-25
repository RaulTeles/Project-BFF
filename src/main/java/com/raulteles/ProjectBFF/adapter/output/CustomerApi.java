package com.raulteles.ProjectBFF.adapter.output;

import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-api", url = "http://localhost:8080")
public interface CustomerApi {

    @GetMapping("/cliente/{id}")
    CustomerDTO getCustomerById(@PathVariable Long id);

    @GetMapping("/cliente/name/{customerName}")
    CustomerDTO getCustomerByName(@PathVariable String customerName);

    @GetMapping("/cliente/documentNumber")
    CustomerDTO getCustomerByDocumentNumber(@RequestParam String number);

}
