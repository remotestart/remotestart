package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findAllByTeamId(Long id);

}
