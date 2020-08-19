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

    @Query(value = "SELECT roles.id FROM roles\n" +
            "JOIN user_team_role on roles.id = user_team_role.role_id\n" +
            "JOIN teams t on user_team_role.team_id = t.id\n" +
            "JOIN users u on user_team_role.user_id = u.id\n" +
            "WHERE user_id = :userId AND team_id = :teamId", nativeQuery = true)
    List<Long> userRoleByUserTeamId(@Param("userId") long userId, @Param("teamId") long teamId);
}
