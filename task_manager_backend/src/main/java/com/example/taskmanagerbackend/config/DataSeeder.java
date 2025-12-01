package com.example.taskmanagerbackend.config;

import com.example.taskmanagerbackend.model.Task;
import com.example.taskmanagerbackend.service.TaskService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Seeds initial data on application startup for quick testing.
 */
@Component
public class DataSeeder implements ApplicationRunner {

    private final TaskService taskService;

    public DataSeeder(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!taskService.getAll().isEmpty()) return;

        Task t1 = Task.newTask("Buy groceries", "Milk, eggs, bread", Instant.now().plus(2, ChronoUnit.DAYS));
        Task t2 = Task.newTask("Finish report", "Complete Q4 financial report", Instant.now().plus(5, ChronoUnit.DAYS));
        Task t3 = Task.newTask("Workout", "30 minutes cardio", null);
        t2.setCompleted(true);

        taskService.addAll(List.of(t1, t2, t3));
    }
}
