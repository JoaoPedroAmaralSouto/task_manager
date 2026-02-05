package com.joaopedroamaral.taskManager.DTO;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record CompanyResponseDTO(Long id,
                                 String companyName,
                                 LocalDateTime createdAt) {
}
