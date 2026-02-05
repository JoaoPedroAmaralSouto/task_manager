package com.joaopedroamaral.taskManager.Controller;


import com.joaopedroamaral.taskManager.DTO.TaskRequestDTO;
import com.joaopedroamaral.taskManager.DTO.TaskResponseDTO;
import com.joaopedroamaral.taskManager.Service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponseDTO responseDTO(@RequestBody TaskRequestDTO dto){
        return taskService.create(dto);
    }

    @GetMapping
    public List<TaskResponseDTO> listAll(){
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO findByID(@PathVariable long id){
        return taskService.findByID(id);
    }
}
