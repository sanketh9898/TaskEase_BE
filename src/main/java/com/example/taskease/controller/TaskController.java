package com.example.taskease.controller;

import com.example.taskease.dto.TaskDto;
import com.example.taskease.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")

public class TaskController {

    @Autowired
    private ITaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, Authentication authentication) {
        String username = authentication.getName(); // Get the username of the authenticated user
        TaskDto createdTask = taskService.createTask(taskDto, username);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId, Authentication authentication) {
        String username = authentication.getName();
        TaskDto task = taskService.getTaskById(taskId, username);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(Authentication authentication) {
        String username = authentication.getName();
        List<TaskDto> tasks = taskService.getAllTasksByUser(username);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto, Authentication authentication) {
        String username = authentication.getName();
        TaskDto updatedTask = taskService.updateTask(taskId, taskDto, username);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId, Authentication authentication) {
        String username = authentication.getName();
        taskService.deleteTask(taskId, username);
        return ResponseEntity.noContent().build();
    }
}