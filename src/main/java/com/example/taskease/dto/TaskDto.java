package com.example.taskease.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Long taskId;
    private String name;
    private String description;
    private LocalDateTime taskTime;
    private String type;
    private Long userId; // Include userId to associate the task with a user
}