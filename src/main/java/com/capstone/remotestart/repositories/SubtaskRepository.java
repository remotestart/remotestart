package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    List<Subtask> findAllByTaskId(Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM sub_tasks WHERE task_id = :task_id", nativeQuery = true)
    void deleteSubtasksFromTaskId(@Param("task_id") long taskId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE sub_tasks SET title = :title, description = :description WHERE id = :id", nativeQuery = true)
    void editSubtaskById(@Param("id") long id, @Param("title") String title, @Param("description") String description);
}
