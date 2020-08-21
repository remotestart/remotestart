package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Project;
import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.repositories.*;
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
    private UserRepository userDao;

    public ProjectController(ProjectRepository projectDao, TeamRepository teamDao, TaskRepository taskDao, SubtaskRepository subTaskDao, UserRepository userDao) {
        this.projectDao = projectDao;
        this.teamDao = teamDao;
        this.taskDao = taskDao;
        this.subTaskDao = subTaskDao;
        this.userDao = userDao;
    }

    @GetMapping("/project/create/{teamId}")
    public String createProject(Model model, @PathVariable long teamId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("project", new Project());
        model.addAttribute("teamID", teamId);
        if (userDao.checkIfTeamLeader(user.getId(), teamId) != 1) {
            return "redirect:/teams";
        } else {
            return "projects/create-project";
        }
    }

    @PostMapping("/project/create/{teamId}")
    public String saveProject(@ModelAttribute Project project, @PathVariable long teamId) {
        project.setTeam(teamDao.getOne(teamId));
        projectDao.save(project);
        return "redirect:/team/" +teamId;
    }

    @GetMapping("/projects")
    private String showProjectPage(Model model){
        model.addAttribute("projects", projectDao.findAll());
        return "projects/projects";
    }

    @GetMapping("/project/{id}")
    private String projectPage(Model model, @PathVariable long id){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("role", userDao.checkIfTeamLeader(loggedInUser.getId(),projectDao.teamIdFromProjectId(id)));
        model.addAttribute("project", projectDao.getOne(id));
        model.addAttribute("user", loggedInUser);
        if (userDao.checkIfOnTeam(loggedInUser.getId(), projectDao.teamIdFromProjectId(id)) == null) {
            return "redirect:/teams";
        } else {
            return "projects/project";
        }
    }

    @GetMapping("/project/{id}/{userId}")
    private String teamMemberPage(Model model, @PathVariable long id, @PathVariable long userId){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("project", projectDao.getOne(id));
        model.addAttribute("subtasks", subTaskDao.findAll());
        model.addAttribute("tasks", taskDao.findAllByUserAndProjectId(id,userId));
        if (loggedInUser.getId() != userId) {
            return "redirect:/teams";
        } else {
            return "tasks/team-member-tasks";
        }
    }

    @PostMapping("/project/{projectId}/delete")
    private String deleteProject(@PathVariable long projectId){
        return "redirect:/team/" + projectDao.teamIdFromProjectId(projectId);
    }
}
