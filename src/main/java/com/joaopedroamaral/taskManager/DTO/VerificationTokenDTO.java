package com.joaopedroamaral.taskManager.DTO;

import java.time.LocalDateTime;

public record VerificationTokenDTO (String token,
                                    LocalDateTime localDateTime){
}
