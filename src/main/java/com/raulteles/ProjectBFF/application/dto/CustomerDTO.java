package com.raulteles.ProjectBFF.application.dto;

import java.util.List;

public record CustomerDTO(Long id, String name, String segmentName, List<CustomerDocumentDTO> documents, List<CustomerContactDTO> contacts) {
}
