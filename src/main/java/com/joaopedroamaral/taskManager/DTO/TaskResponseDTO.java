package com.joaopedroamaral.taskManager.DTO;

import com.joaopedroamaral.taskManager.Entity.Status;

public record TaskResponseDTO (Long id,
                               Long userId,
                               String title,
                               String  description,
                               Status status){
}
