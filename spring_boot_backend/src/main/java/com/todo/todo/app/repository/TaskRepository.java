package com.todo.todo.app.repository;

import com.todo.todo.app.models.TaskItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskItems,Long> {

}
