package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByTeamId(Long id);

    @Query(value = "SELECT team_id FROM projects WHERE id = :projectId", nativeQuery = true)
    Long teamIdFromProjectId(@Param("projectId") long projectId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM projects WHERE id = :id", nativeQuery = true)
    void editProjectById(@Param("id") long id);
}
