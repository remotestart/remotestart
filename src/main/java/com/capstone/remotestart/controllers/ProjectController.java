package com.capstone.remotestart.controllers;

import com.capstone.remotestart.repositories.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
    private ProjectRepository projectDao;

    public ProjectController(ProjectRepository projectDao) {
        this.projectDao = projectDao;
    }

//    @GetMapping()
}
