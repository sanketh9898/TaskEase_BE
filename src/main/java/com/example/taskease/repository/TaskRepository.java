package com.example.taskease.repository;

import com.example.taskease.model.Task;
import com.example.taskease.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByTaskTimeBetween(LocalDateTime start, LocalDateTime end);
}