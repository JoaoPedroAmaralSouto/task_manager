package com.joaopedroamaral.taskManager.DTO;

public record UserRequestDTO(
        String username,
        String email,
        String password) {
}
