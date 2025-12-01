package com.example.taskmanagerbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO set for Task API.
 */
public class TaskDtos {

    /**
     * Request DTO for creating a Task.
     */
    public static class CreateTaskRequest {
        @Schema(description = "Title of the task", example = "Buy groceries", maxLength = 120, requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "title is required")
        @Size(max = 120, message = "title must be at most 120 characters")
        private String title;

        @Schema(description = "Optional description for the task", example = "Buy milk, eggs, and bread", maxLength = 1000)
        @Size(max = 1000, message = "description must be at most 1000 characters")
        private String description;

        @Schema(description = "Optional due date in ISO-8601 instant", example = "2030-12-25T12:00:00Z")
        private Instant dueDate;

        public CreateTaskRequest() {}

        public String getTitle() { return title; }

        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }

        public void setDescription(String description) { this.description = description; }

        public Instant getDueDate() { return dueDate; }

        public void setDueDate(Instant dueDate) { this.dueDate = dueDate; }
    }

    /**
     * Request DTO for updating a Task.
     */
    public static class UpdateTaskRequest {
        @Schema(description = "Title of the task", example = "Buy groceries and snacks", maxLength = 120)
        @NotBlank(message = "title is required")
        @Size(max = 120, message = "title must be at most 120 characters")
        private String title;

        @Schema(description = "Optional description for the task", example = "Add chips and fruits", maxLength = 1000)
        @Size(max = 1000, message = "description must be at most 1000 characters")
        private String description;

        @Schema(description = "Mark as completed", example = "true")
        private Boolean completed;

        @Schema(description = "Optional due date in ISO-8601 instant", example = "2030-12-31T23:59:59Z")
        private Instant dueDate;

        public UpdateTaskRequest() {}

        public String getTitle() { return title; }

        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }

        public void setDescription(String description) { this.description = description; }

        public Boolean getCompleted() { return completed; }

        public void setCompleted(Boolean completed) { this.completed = completed; }

        public Instant getDueDate() { return dueDate; }

        public void setDueDate(Instant dueDate) { this.dueDate = dueDate; }
    }

    /**
     * Response DTO for Task data exposed by API.
     */
    public static class TaskResponse {
        private UUID id;
        private String title;
        private String description;
        private boolean completed;
        private Instant createdAt;
        private Instant updatedAt;
        private Instant dueDate;

        public TaskResponse() {}

        public TaskResponse(UUID id, String title, String description, boolean completed, Instant createdAt, Instant updatedAt, Instant dueDate) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.completed = completed;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.dueDate = dueDate;
        }

        public UUID getId() { return id; }

        public void setId(UUID id) { this.id = id; }

        public String getTitle() { return title; }

        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }

        public void setDescription(String description) { this.description = description; }

        public boolean isCompleted() { return completed; }

        public void setCompleted(boolean completed) { this.completed = completed; }

        public Instant getCreatedAt() { return createdAt; }

        public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

        public Instant getUpdatedAt() { return updatedAt; }

        public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

        public Instant getDueDate() { return dueDate; }

        public void setDueDate(Instant dueDate) { this.dueDate = dueDate; }
    }
}
