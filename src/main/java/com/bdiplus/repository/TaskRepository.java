package com.bdiplus.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bdiplus.entity.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	
    // Create/Add a New Task
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO task (title, description) VALUES (:title, :description)", nativeQuery = true)
    void createTask(@Param("title") String title, @Param("description") String description);

    // Retrieve a Task by Its ID
    @Query(value = "SELECT * FROM task WHERE id = :taskId", nativeQuery = true)
    Task getTaskById(@Param("taskId") Long taskId);

    // Retrieve a List of All Tasks
    @Query(value = "SELECT * FROM task", nativeQuery = true)
    List<Task> getAllTasks();

    // Update an Existing Task
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET title = :title, description = :description WHERE id = :taskId", nativeQuery = true)
    void updateTask(@Param("taskId") Long taskId, @Param("title") String title, @Param("description") String description);

    // Delete a Task by Its ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM task WHERE id = :taskId", nativeQuery = true)
    void deleteTaskById(@Param("taskId") Long taskId);

		
}
