package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM tasks WHERE project_id = :project_id AND user_id = :user_id", nativeQuery = true)
    List<Task> findAllByUserAndProjectId(@Param("project_id") long projectId, @Param("user_id") long userId);
}
