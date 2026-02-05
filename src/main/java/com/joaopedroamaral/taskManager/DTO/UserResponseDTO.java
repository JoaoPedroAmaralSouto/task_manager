package com.joaopedroamaral.taskManager.DTO;

public record UserResponseDTO (String email,
                                Long id,
                               String username,
                               boolean admin,
                               boolean enabled) {
}
