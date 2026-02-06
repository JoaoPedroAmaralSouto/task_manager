package com.joaopedroamaral.taskManager.DTO;

import com.joaopedroamaral.taskManager.Entity.Status;

public record TaskRequestDTO(
        String title,
        String description,
        Status status) {
}
