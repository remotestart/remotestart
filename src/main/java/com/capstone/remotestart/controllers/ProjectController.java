package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Project;
import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.repositories.ProjectRepository;
import com.capstone.remotestart.repositories.TeamRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    private ProjectRepository projectDao;
    private TeamRepository teamDao;

    public ProjectController(ProjectRepository projectDao, TeamRepository teamDao) {
        this.projectDao = projectDao;
        this.teamDao = teamDao;
    }

    @GetMapping("/project/create")
    public String createProject(Model model) {
        model.addAttribute("project", new Project());
        return "projects/create-project";
    }

    @PostMapping("/project/create")
    public String saveProject(@ModelAttribute Project project) {
        projectDao.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects")
    private String showProjectPage(Model model){
        model.addAttribute("projects", projectDao.findAll());
        return "projects/projects";
    }

    @GetMapping("/project/{id}")
    private String projectPage(Model model, @PathVariable long id){
        model.addAttribute("project", projectDao.getOne(id));
        return "projects/project";
    }
}
