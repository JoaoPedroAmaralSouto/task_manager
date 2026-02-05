package com.joaopedroamaral.taskManager.Controller;

import com.joaopedroamaral.taskManager.DTO.UserResponseDTO;
import com.joaopedroamaral.taskManager.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    public List<UserResponseDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public UserResponseDTO findById(@PathVariable Long id){
        return service.findByID(id);
    }

    @GetMapping("/email/{email}")
    public UserResponseDTO findByEmail(@PathVariable String email){
        return service.findByEmail(email);
    }


}
