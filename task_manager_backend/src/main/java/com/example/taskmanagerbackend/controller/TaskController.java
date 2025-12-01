package com.example.taskmanagerbackend.controller;

import com.example.taskmanagerbackend.dto.TaskDtos.CreateTaskRequest;
import com.example.taskmanagerbackend.dto.TaskDtos.TaskResponse;
import com.example.taskmanagerbackend.dto.TaskDtos.UpdateTaskRequest;
import com.example.taskmanagerbackend.model.Task;
import com.example.taskmanagerbackend.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "CRUD operations for managing tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private TaskResponse toResponse(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.isCompleted(),
                t.getCreatedAt(),
                t.getUpdatedAt(),
                t.getDueDate()
        );
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List tasks",
            description = "Returns all tasks sorted by creation time descending.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of tasks",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TaskResponse.class))))
            }
    )
    public List<TaskResponse> listTasks() {
        return taskService.getAll().stream().map(this::toResponse).toList();
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get task by ID",
            description = "Returns a single task by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task found",
                            content = @Content(schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    public TaskResponse getTask(
            @Parameter(description = "UUID of the task", required = true)
            @PathVariable("id") UUID id) {
        return toResponse(taskService.getById(id));
    }

    // PUBLIC_INTERFACE
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new task",
            description = "Creates a task with the provided details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Task created",
                            content = @Content(schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest req) {
        Task created = taskService.create(req.getTitle(), req.getDescription(), req.getDueDate());
        return toResponse(created);
    }

    // PUBLIC_INTERFACE
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update a task",
            description = "Updates the existing task identified by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task updated",
                            content = @Content(schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    public TaskResponse updateTask(
            @Parameter(description = "UUID of the task", required = true)
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateTaskRequest req) {
        Task updated = taskService.update(id, req.getTitle(), req.getDescription(), req.getCompleted(), req.getDueDate());
        return toResponse(updated);
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a task",
            description = "Deletes the task with the given ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Task deleted"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    public void deleteTask(
            @Parameter(description = "UUID of the task", required = true)
            @PathVariable("id") UUID id) {
        taskService.delete(id);
    }
}
