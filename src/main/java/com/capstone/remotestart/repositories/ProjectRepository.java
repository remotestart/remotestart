package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByTeamId(Long id);
}
