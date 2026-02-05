package com.joaopedroamaral.taskManager.Service;

import com.joaopedroamaral.taskManager.DTO.TaskRequestDTO;
import com.joaopedroamaral.taskManager.DTO.TaskResponseDTO;
import com.joaopedroamaral.taskManager.Repository.TasksRepository;
import com.joaopedroamaral.taskManager.Repository.UserRepository;
import com.joaopedroamaral.taskManager.Entity.Status;
import com.joaopedroamaral.taskManager.Entity.Task;
import com.joaopedroamaral.taskManager.Entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class TaskService {
    private final TasksRepository tasksRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TaskService(TasksRepository tasksRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.tasksRepository = tasksRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TaskResponseDTO create(TaskRequestDTO dto) {
        if (userRepository.findByEmail(dto.email()).isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userRepository.findByEmail(dto.email()).orElseThrow( () ->new EntityNotFoundException("User not found"));
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        Task task = new Task(dto.title(), dto.description(), Status.PENDING, user.getId());
        Task savedTask = tasksRepository.save(task);
        return new TaskResponseDTO(savedTask.getId(),
                savedTask.getUserID(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus());
    }

    public List<TaskResponseDTO> findAll(){
        return tasksRepository.findAll()
                .stream()
                .map(task -> new TaskResponseDTO(task.getId(),
                        task.getUserID(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus()))
                .toList();
    }

    public TaskResponseDTO findByID(long id){
        Task task = tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new TaskResponseDTO(task.getId(),
                task.getUserID(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus());
    }
}
