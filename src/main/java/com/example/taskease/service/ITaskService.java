package com.example.taskease.service;

import com.example.taskease.dto.TaskDto;
import com.example.taskease.model.Task;

import java.util.List;

public interface ITaskService {
    TaskDto createTask(TaskDto taskDto, String username);
    TaskDto getTaskById(Long taskId, String username);
    List<TaskDto> getAllTasksByUser(String username);
    TaskDto updateTask(Long taskId, TaskDto taskDto, String username);
    void deleteTask(Long taskId, String username);

    TaskDto convertEntityToDto(Task task);
    Task convertDtoToEntity(TaskDto taskDto);
}