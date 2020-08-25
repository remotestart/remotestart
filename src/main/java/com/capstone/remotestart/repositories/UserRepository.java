package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
    Long checkIfTeamLeader(@Param("userId") long userId, @Param("teamId") long teamId);

    @Query(value = "SELECT teams.id FROM teams\n" +
            "JOIN user_team_role on teams.id = user_team_role.team_id\n" +
            "JOIN roles r on user_team_role.role_id = r.id\n" +
            "JOIN users u on user_team_role.user_id = u.id\n" +
            "WHERE user_id = :userId AND team_id = :teamId", nativeQuery = true)
    Long checkIfOnTeam(@Param("userId") long userId, @Param("teamId") long teamId);

    @Query(value = "SELECT users.id FROM users\n" +
            "JOIN user_team_role on users.id = user_team_role.user_id\n" +
            "JOIN roles r on user_team_role.role_id = r.id\n" +
            "JOIN teams t on user_team_role.team_id = t.id\n" +
            "WHERE role_id = 1 AND team_id = :teamId", nativeQuery = true)
    Long findTeamLeaderByTeamId(@Param("teamId") long teamId);

   @Transactional
   @Modifying
    @Query(value = "UPDATE users SET username = :username, first_name = :firstName, last_name =:lastName, email = :email WHERE id = :id", nativeQuery = true)
    void editProfileInfo(@Param("id") long id,
                         @Param("username") String username,
                         @Param("firstName") String firstName,
                         @Param("lastName") String lastName,
                         @Param("email") String email);

   @Transactional
    @Modifying
    @Query(value = "UPDATE users SET password = :password WHERE id = :id", nativeQuery = true)
    void editPassword(@Param("id") Long id, @Param("password") String password);
}
