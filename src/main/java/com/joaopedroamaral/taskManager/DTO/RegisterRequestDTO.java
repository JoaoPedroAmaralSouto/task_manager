package com.joaopedroamaral.taskManager.DTO;

public record RegisterRequestDTO(
        UserRequestDTO user,
        CompanyRequestDTO company
) {}
