package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
