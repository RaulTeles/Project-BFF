package com.raulteles.ProjectBFF.adapter.output;

import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-api", url = "http://localhost:8080")
public interface CustomerApi {

    @GetMapping("/cliente/{id}")
    CustomerDTO getCustomerById(@PathVariable Long id);

    @GetMapping("/cliente/name/{customerName}")
    CustomerDTO getCustomerByName(@PathVariable String customerName);

}
