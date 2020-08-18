package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmailIgnoreCase(String email);

    @Query(value = "SELECT user_id FROM user_team_role WHERE team_id = :id", nativeQuery = true)
    List<Long> allUsersByTeamId(@Param("id") long id);

}
