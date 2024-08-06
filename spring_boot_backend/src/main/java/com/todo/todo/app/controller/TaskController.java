package com.todo.todo.app.controller;

import com.todo.todo.app.models.TaskItems;
import com.todo.todo.app.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @PostMapping(path = "/add")
    public TaskItems addtask(@Valid @RequestBody TaskItems taskItems){
        return taskRepository.save(taskItems);
    }

    @GetMapping(path = "/getAllTasks")
    public List<TaskItems> getAllTasks(){
        return taskRepository.findAll();
    }


    @PutMapping(path = "/update/{id}")
    public ResponseEntity updateTask(@PathVariable Long id){
        boolean exist = taskRepository.existsById(id);
        if(exist){
            TaskItems task = taskRepository.getById(id);
            boolean done = task.isDone();
            task.setDone(!done);
            taskRepository.save(task);
            return ResponseEntity.ok("Task is updated");
        }
        else {
         return   ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task is not exist");
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        boolean exist = taskRepository.existsById(id);
        if(exist){
            taskRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Task is deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task is Not Exist");
    }
}
