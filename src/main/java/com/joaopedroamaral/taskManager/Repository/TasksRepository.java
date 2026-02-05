package com.joaopedroamaral.taskManager.Repository;

import com.joaopedroamaral.taskManager.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Long> {
}
