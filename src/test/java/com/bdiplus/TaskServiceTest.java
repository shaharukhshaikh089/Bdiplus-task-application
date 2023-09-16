package com.bdiplus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bdiplus.entity.Task;
import com.bdiplus.repository.TaskRepository;
import com.bdiplus.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

	
	@Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testCreateTask() {
        // Arrange
        Task newTask = new Task();
        newTask.setTitle("Test Task");
        newTask.setDescription("Test Description");

        Task savedTask = new Task();
        savedTask.setId(1);
        savedTask.setTitle("Test Task");
        savedTask.setDescription("Test Description");

        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(savedTask);

        // Act
        Task createdTask = taskService.createTask(newTask);

        // Assert
        assertNotNull(createdTask.getId());
        assertEquals("Test Task", createdTask.getTitle());
        assertEquals("Test Description", createdTask.getDescription());
    }

    @Test
    public void testGetTaskById() {
        // Arrange
        int taskId = 1;
        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Test Task");
        task.setDescription("Test Description");

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Act
        Task foundTask = taskService.getTaskById(taskId);

        // Assert
        assertNotNull(foundTask);
        assertEquals(taskId, foundTask.getId());
        assertEquals("Test Task", foundTask.getTitle());
        assertEquals("Test Description", foundTask.getDescription());
    }

    @Test
    public void testGetAllTasks() {
        // Arrange
        Task task1 = new Task();
        task1.setId(1);
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");

        Task task2 = new Task();
        task2.setId(2);
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");

        List<Task> tasks = Arrays.asList(task1, task2);

        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        // Act
        List<Task> allTasks = taskService.getAllTasks();

        // Assert
        assertNotNull(allTasks);
        assertEquals(2, allTasks.size());
    }

    @Test
    public void testUpdateTask() {
        // Arrange
        int taskId = 1;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTitle("Existing Task");
        existingTask.setDescription("Existing Description");

        Task updatedTask = new Task();
        updatedTask.setId(taskId);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(updatedTask);

        // Act
        Task result = taskService.updateTask(taskId, updatedTask);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    public void testDeleteTask() {
        // Arrange
        int taskId = 1;
        Task existingTask = new Task();
        existingTask.setId(taskId);

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));

        // Act
        taskService.deleteTask(taskId);

        // Assert (verify that the delete method was called)
        Mockito.verify(taskRepository, Mockito.times(1)).delete(existingTask);
    }
}
