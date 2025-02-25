package com.raulteles.ProjectBFF.application.dto;

import java.util.List;

public record CreateCustomerDTO(String name, Long segmentId, List<CustomerDocumentDTO> documents,
                                List<CustomerContactDTO> contacts) {
}
