package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Task;
import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.models.UserTeamRoleLink;
import com.capstone.remotestart.repositories.ProjectRepository;
import com.capstone.remotestart.repositories.TaskRepository;
import com.capstone.remotestart.repositories.TeamRepository;
import com.capstone.remotestart.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    private TaskRepository taskDao;
    private UserRepository userDao;
    private TeamRepository teamDao;
    private ProjectRepository projectDao;


    public TaskController(TaskRepository taskDao, UserRepository userDao, TeamRepository teamDao, ProjectRepository projectDao) {
        this.taskDao = taskDao;
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.projectDao = projectDao;
    }

    @GetMapping("/task/create/{projectId}")
    public String createTask(Model model, @PathVariable long projectId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("projectId", projectId);
        model.addAttribute("task", new Task());
        model.addAttribute("users", userDao.findAll());

        if (userDao.checkIfTeamLeader(user.getId(), projectDao.teamIdFromProjectId(projectId)) != 1) {
            return "redirect:/teams";
        } else {
            return "tasks/create-task";
        }
    }

    @PostMapping("/task/create/{projectId}")
    public String saveTask(@ModelAttribute Task task, @RequestParam(name = "userId") long userId, @PathVariable long projectId) {
        task.setProject(projectDao.getOne(projectId));
        task.setUser(userDao.getOne(userId));
        taskDao.save(task);
        return "redirect:/task";
    }

    @GetMapping("/task")
    private String showTask(Model model) {
        model.addAttribute("task", taskDao.findAll());
        return "tasks/task";
    }

}
