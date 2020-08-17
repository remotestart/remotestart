package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.UserTeamRoleLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTeamRoleRepository extends JpaRepository <UserTeamRoleLink, Long> {
}
