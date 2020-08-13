package com.capstone.remotestart.Repositories;

import com.capstone.remotestart.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
