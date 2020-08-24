package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByTeamId(Long id);

    @Query(value = "SELECT team_id FROM projects WHERE id = :projectId", nativeQuery = true)
    Long teamIdFromProjectId(@Param("projectId") long projectId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE projects SET name = :name, description = :description, start_date = :startDate, deadline = :deadline WHERE id = :id", nativeQuery = true)
    void editProjectById(@Param("id") long id ,@Param("name") String name, @Param("description") String description, @Param("startDate") String startDate, @Param("deadline") String deadline);

    @Transactional
    @Modifying
    @Query(value = "UPDATE projects SET completion_date = :completion_date WHERE id = :id", nativeQuery = true)
    void updateProjectCompletionDateById(@Param("id") long id, @Param("completion_date") Date completionDate);
}
