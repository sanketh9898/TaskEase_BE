// src/main/java/com/example/taskease/dto/TaskDto.java
package com.example.taskease.dto;

import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat; // Import this

@Data
public class TaskDto {
    private Long taskId;
    private String name;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Add this annotation
    private LocalDateTime taskTime;

    private String type;
    private Long userId;
}