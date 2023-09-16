package com.bdiplus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdiplus.entity.Task;
import com.bdiplus.service.TaskService;
import com.bdiplus.util.TaskCreationException;
import com.bdiplus.util.TaskDeletionException;
import com.bdiplus.util.TaskNotFoundException;
import com.bdiplus.util.TaskUpdateException;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	private  final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            throw new TaskNotFoundException("Task with ID " + id + " not found.");
        }
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        if (createdTask != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } else {
            throw new TaskCreationException("Failed to create task.");
        }
    }
    @PutMapping("/{id}")
        public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        Task updated = taskService.updateTask(id, updatedTask);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            throw new TaskUpdateException("Failed to update task with ID " + id);
        }
    }

    @DeleteMapping("/{id}")    
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            throw new TaskDeletionException("Failed to delete task with ID " + id);
        }
    }
}
