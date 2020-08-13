package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
