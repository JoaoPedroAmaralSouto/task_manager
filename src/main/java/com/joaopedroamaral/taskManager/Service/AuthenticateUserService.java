package com.joaopedroamaral.taskManager.Service;

import com.joaopedroamaral.taskManager.DTO.UserResponseDTO;
import com.joaopedroamaral.taskManager.Entity.User;
import com.joaopedroamaral.taskManager.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticateUserService(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean verifyPassword(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if(!verifyIfEnabled(user))
            return false;
        return passwordEncoder.matches(password, user.getPassword());
    }

    private boolean verifyIfEnabled(User user){
       return user.getEnabled();
    }
}
