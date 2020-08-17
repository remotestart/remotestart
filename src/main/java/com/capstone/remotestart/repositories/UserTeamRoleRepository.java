package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.User;
import com.capstone.remotestart.models.UserTeamRoleLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTeamRoleRepository extends JpaRepository <UserTeamRoleLink, Long> {
//    List<User> findAllByTeamId
}
