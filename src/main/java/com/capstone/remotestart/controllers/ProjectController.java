package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Project;
import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.repositories.ProjectRepository;
import com.capstone.remotestart.repositories.SubtaskRepository;
import com.capstone.remotestart.repositories.TaskRepository;
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
    private TaskRepository taskDao;
    private SubtaskRepository subTaskDao;

    public ProjectController(ProjectRepository projectDao, TeamRepository teamDao, TaskRepository taskDao, SubtaskRepository subTaskDao) {
        this.projectDao = projectDao;
        this.teamDao = teamDao;
        this.taskDao = taskDao;
        this.subTaskDao = subTaskDao;
    }

    @GetMapping("/project/create/{teamId}")
    public String createProject(Model model, @PathVariable long teamId) {
        model.addAttribute("project", new Project());
        model.addAttribute("teamID", teamId);
        return "projects/create-project";
    }

    @PostMapping("/project/create/{teamId}")
    public String saveProject(@ModelAttribute Project project, @PathVariable long teamId) {
        project.setTeam(teamDao.getOne(teamId));
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
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("project", projectDao.getOne(id));
        model.addAttribute("user", loggedInUser);
        return "projects/project";
    }

    @GetMapping("/project/{id}/{userId}")
    private String teamMemberPage(Model model, @PathVariable long id, @PathVariable long userId){
        model.addAttribute("subtasks", subTaskDao.findAll());
        model.addAttribute("tasks", taskDao.findAllByUserAndProjectId(id,userId));
        return "tasks/team-member-tasks";
    }
}
