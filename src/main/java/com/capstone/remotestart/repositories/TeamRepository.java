package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "SELECT team_id FROM user_team_role WHERE user_id = :id", nativeQuery = true)
    List<Long> allTeamsByUserId(@Param("id") long id);
}
