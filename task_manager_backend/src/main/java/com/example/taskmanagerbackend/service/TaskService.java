package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.exception.NotFoundException;
import com.example.taskmanagerbackend.model.Task;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service layer containing business logic for task management.
 * Uses in-memory storage backed by ConcurrentHashMap.
 */
@Service
public class TaskService {

    private final ConcurrentHashMap<UUID, Task> store = new ConcurrentHashMap<>();

    // PUBLIC_INTERFACE
    public List<Task> getAll() {
        /**
         * Returns all tasks sorted by createdAt descending.
         */
        return store.values().stream()
                .sorted(Comparator.comparing(Task::getCreatedAt).reversed())
                .toList();
    }

    // PUBLIC_INTERFACE
    public Task getById(UUID id) {
        /**
         * Returns a task by ID.
         * Throws NotFoundException if the task does not exist.
         */
        Task t = store.get(id);
        if (t == null) throw new NotFoundException("Task with id " + id + " not found");
        return t;
    }

    // PUBLIC_INTERFACE
    public Task create(String title, String description, Instant dueDate) {
        /**
         * Creates a new task and stores it.
         */
        Task task = Task.newTask(title, description, dueDate);
        store.put(task.getId(), task);
        return task;
    }

    // PUBLIC_INTERFACE
    public Task update(UUID id, String title, String description, Boolean completed, Instant dueDate) {
        /**
         * Updates an existing task fields.
         * Throws NotFoundException if the task does not exist.
         */
        Task existing = getById(id);
        if (title != null) existing.setTitle(title);
        if (description != null) existing.setDescription(description);
        if (completed != null) existing.setCompleted(completed);
        if (dueDate != null || (dueDate == null)) {
            existing.setDueDate(dueDate);
        }
        existing.setUpdatedAt(Instant.now());
        return existing;
    }

    // PUBLIC_INTERFACE
    public void delete(UUID id) {
        /**
         * Deletes a task by ID. Throws NotFoundException if not found.
         */
        Task removed = store.remove(id);
        if (removed == null) {
            throw new NotFoundException("Task with id " + id + " not found");
        }
    }

    /**
     * Adds a batch of tasks. Useful for seeding.
     */
    public List<Task> addAll(List<Task> tasks) {
        List<Task> created = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getId() == null) {
                t.setId(UUID.randomUUID());
            }
            if (t.getCreatedAt() == null) t.setCreatedAt(Instant.now());
            t.setUpdatedAt(Instant.now());
            store.put(t.getId(), t);
            created.add(t);
        }
        return created;
    }
}
