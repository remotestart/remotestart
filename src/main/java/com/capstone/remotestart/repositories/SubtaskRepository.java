package com.capstone.remotestart.repositories;

import com.capstone.remotestart.models.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
}
