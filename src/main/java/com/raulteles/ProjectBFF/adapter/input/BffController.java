package com.raulteles.ProjectBFF.adapter.input;

import com.raulteles.ProjectBFF.application.dto.CreateCustomerDTO;
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
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = bffInputPort.getCustomerById(id);
        return customer;
    }

    @GetMapping("/cliente/name/{customerName}")
    public CustomerDTO getCustomerByName(@PathVariable String customerName) {
        CustomerDTO customer = bffInputPort.getCustomerByName(customerName);
        return customer;
    }

    @GetMapping("/cliente/documentNumber")
    public CustomerDTO getCustomerByDocumentNumber(@RequestParam String number){
        CustomerDTO document = bffInputPort.getCustomerByDocumentNumber(number);
        return document;
    }

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
     public CustomerDTO createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        CustomerDTO save =bffInputPort.createCustomer(createCustomerDTO);
        return save;
     }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
