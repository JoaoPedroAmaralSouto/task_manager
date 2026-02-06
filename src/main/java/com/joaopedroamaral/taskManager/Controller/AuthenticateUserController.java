package com.joaopedroamaral.taskManager.Controller;

import com.joaopedroamaral.taskManager.DTO.LoginRequestDTO;
import com.joaopedroamaral.taskManager.DTO.LoginResponseDTO;
import com.joaopedroamaral.taskManager.Service.AuthenticateUserService;
import com.joaopedroamaral.taskManager.Service.VerificationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class AuthenticateUserController {

    private final AuthenticateUserService authenticateUserService;
    private final VerificationTokenService verificationTokenService;

    public AuthenticateUserController(AuthenticateUserService authenticateUserService,
                                      VerificationTokenService verificationTokenService){
        this.authenticateUserService = authenticateUserService;
        this.verificationTokenService = verificationTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> verifyUserPassword(@RequestBody LoginRequestDTO dto){
        boolean verify = authenticateUserService.verifyPassword(dto.email(), dto.password());
        String token = verificationTokenService.AuthenticateLogin(dto.email());
        if(!verify)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(new LoginResponseDTO(null, null));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new LoginResponseDTO(dto.email(), token));
    }
}
