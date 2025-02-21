package com.raulteles.ProjectBFF.adapter.input;

import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import com.raulteles.ProjectBFF.application.port.input.BffInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bff")
public class BffController {

    private final BffInputPort bffInputPort;


    public BffController(BffInputPort bffInputPort) {
        this.bffInputPort = bffInputPort;
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = bffInputPort.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/cliente/name/{customerName}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String customerName) {
        CustomerDTO customer = bffInputPort.getCustomerByName(customerName);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
