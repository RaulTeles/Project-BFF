package com.raulteles.ProjectBFF.adapter.input;

import com.raulteles.ProjectBFF.application.dto.CustomerDTO;
import com.raulteles.ProjectBFF.application.port.input.BffInputPort;
import com.raulteles.ProjectBFF.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/cliente/name/{customerName}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String customerName) {
        CustomerDTO customer = bffInputPort.getCustomerByName(customerName);
        return ResponseEntity.ok(customer);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
