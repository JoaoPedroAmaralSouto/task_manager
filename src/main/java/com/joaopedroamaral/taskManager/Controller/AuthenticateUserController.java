package com.joaopedroamaral.taskManager.Controller;

import com.joaopedroamaral.taskManager.DTO.LoginRequestDTO;
import com.joaopedroamaral.taskManager.DTO.RegisterResponseDTO;
import com.joaopedroamaral.taskManager.Service.AuthenticateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class AuthenticateUserController {

    private final AuthenticateUserService authenticateUserService;

    public AuthenticateUserController(AuthenticateUserService authenticateUserService){
        this.authenticateUserService = authenticateUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponseDTO> verifyUserPassword(@RequestBody LoginRequestDTO dto){
        boolean verify = authenticateUserService.verifyPassword(dto.email(), dto.password());

        if(!verify)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(new RegisterResponseDTO("Invalid login"));
        return ResponseEntity.ok(new RegisterResponseDTO("Success"));
    }
}
