package com.joaopedroamaral.taskManager.Service;

import com.joaopedroamaral.taskManager.DTO.UserRequestDTO;
import com.joaopedroamaral.taskManager.DTO.UserResponseDTO;
import com.joaopedroamaral.taskManager.Repository.UserRepository;
import com.joaopedroamaral.taskManager.Entity.User;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO register(UserRequestDTO dto){
        Optional<User> optionalUser = userRepository.findByEmail(dto.email());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if(existingUser.getEnabled())
                throw new RuntimeException("Email already exist");
            else{
                existingUser.setAdmin(true);
                existingUser.setPassword(passwordEncoder.encode(dto.password()));
                existingUser.setUsername(dto.username());
                User savedUser = userRepository.save(existingUser);
                return new UserResponseDTO(savedUser.getEmail(), savedUser.getId(), savedUser.getUsername(),
                        savedUser.getAdmin(), savedUser.getEnabled());
            }
        }
        User user = new User(dto.username(), dto.email(), passwordEncoder.encode(dto.password()), true);
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getEmail(), savedUser.getId(), savedUser.getUsername(),
                savedUser.getAdmin(), savedUser.getEnabled());
    }

    public UserResponseDTO createUser(UserRequestDTO dto){
        Optional<User> optionalUser = userRepository.findByEmail(dto.email());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if(existingUser.getEnabled())
                throw new RuntimeException("Email already exist");
            else{
                existingUser.setAdmin(false);
                existingUser.setPassword(passwordEncoder.encode(dto.password()));
                existingUser.setUsername(dto.username());
                User savedUser = userRepository.save(existingUser);
                return new UserResponseDTO(savedUser.getEmail(), savedUser.getId(), savedUser.getUsername(),
                        savedUser.getAdmin(), savedUser.getEnabled());
            }
        }
        User user = new User(dto.username(), dto.email(), passwordEncoder.encode(dto.password()), false);
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getEmail(), savedUser.getId(), savedUser.getUsername(),
                savedUser.getAdmin(), savedUser.getEnabled());
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(user.getEmail(), user.getId(),
                        user.getUsername(), user.getAdmin(), user.getEnabled()))
                .toList();
    }

    public UserResponseDTO findByID(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user.getEmail(), user.getId(), user.getUsername(), user.getAdmin(), user.getEnabled());
    }

    public UserResponseDTO findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user.getEmail(), user.getId(), user.getUsername(), user.getAdmin(), user.getEnabled());
    }
}
