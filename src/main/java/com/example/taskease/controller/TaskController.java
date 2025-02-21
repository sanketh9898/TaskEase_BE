package com.example.taskease.controller;

import com.example.taskease.dto.TaskDto;
import com.example.taskease.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger; // Import Logger
import org.slf4j.LoggerFactory; // Import LoggerFactory

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class); // Logger declaration

    @Autowired
    private ITaskService taskService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(@RequestBody TaskDto taskDto, Authentication authentication) {
        String username = authentication.getName();
        logger.info("TaskController: createTask - Received request.  Username: {}, TaskDto: {}", username, taskDto);

        try {
            TaskDto createdTask = taskService.createTask(taskDto, username);
            logger.info("TaskController: createTask - Task created successfully. TaskDto: {}", createdTask);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Task created successfully");
            response.put("task", createdTask);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            logger.error("TaskController: createTask - Error creating task: ", e); // Log with exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
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

    // In TaskController.java
    @PutMapping("/{taskId}")
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto, Authentication authentication) {
        String username = authentication.getName();
        logger.info("TaskController: updateTask - Received request. TaskID: {}, Username: {}, TaskDto: {}", taskId, username, taskDto);

        try {
            TaskDto updatedTask = taskService.updateTask(taskId, taskDto, username);
            logger.info("TaskController: updateTask - Task updated successfully. TaskDto: {}", updatedTask);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Task updated successfully");
            response.put("task", updatedTask);
            return ResponseEntity.ok(response); // Return 200 OK with the updated task

        } catch (RuntimeException e) {
            logger.error("TaskController: updateTask - Error updating task: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Or a more specific status
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId, Authentication authentication) {
        String username = authentication.getName();
        taskService.deleteTask(taskId, username);
        return ResponseEntity.noContent().build();
    }

}