package com.raulteles.ProjectBFF.adapter.output;

import com.raulteles.ProjectBFF.application.dto.CreateCustomerDTO;
import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import org.apache.catalina.startup.CertificateCreateRule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "customer-api", url = "http://localhost:8080")
public interface CustomerApi {

    @GetMapping("/cliente/{id}")
    CustomerDTO getCustomerById(@PathVariable Long id);

    @GetMapping("/cliente/name/{customerName}")
    CustomerDTO getCustomerByName(@PathVariable String customerName);

    @GetMapping("/cliente/documentNumber")
    CustomerDTO getCustomerByDocumentNumber(@RequestParam String number);

    @PostMapping("/cliente")
    CustomerDTO createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO);
}
