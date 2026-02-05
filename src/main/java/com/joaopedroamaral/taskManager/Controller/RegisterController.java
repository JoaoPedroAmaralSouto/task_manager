package com.joaopedroamaral.taskManager.Controller;

import com.joaopedroamaral.taskManager.DTO.RegisterRequestDTO;
import com.joaopedroamaral.taskManager.DTO.RegisterResponseDTO;
import com.joaopedroamaral.taskManager.DTO.UserRequestDTO;
import com.joaopedroamaral.taskManager.Service.RegisterService;
import com.joaopedroamaral.taskManager.Service.VerificationTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final RegisterService registerService;
    private final VerificationTokenService verificationTokenService;

    public RegisterController(RegisterService registerService, VerificationTokenService verificationTokenService){
        this.registerService = registerService;
        this.verificationTokenService = verificationTokenService;
    }

    @PostMapping
    public ResponseEntity<RegisterResponseDTO>register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        registerService.registerUserAndCompany(registerRequestDTO);
        return ResponseEntity.ok(new RegisterResponseDTO("User and company registered successfully"));
    }

    @PostMapping("/user")
    public ResponseEntity<RegisterResponseDTO>createUser(@RequestBody UserRequestDTO dto){
        registerService.createUser(dto);
        return ResponseEntity.ok(new RegisterResponseDTO("User registered successfully"));
    }

    @GetMapping("/authenticateEmail")
    public ResponseEntity<String> authenticateEmail(@RequestParam String token, @RequestParam String email){
        verificationTokenService.Authenticate(token, email);
        return ResponseEntity.ok("Email authenticated successfully");
    }
}
