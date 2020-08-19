package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Task;
import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.models.UserTeamRoleLink;
import com.capstone.remotestart.repositories.TaskRepository;
import com.capstone.remotestart.repositories.TeamRepository;
import com.capstone.remotestart.repositories.UserRepository;
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


    public TaskController(TaskRepository taskDao, UserRepository userDao, TeamRepository teamDao) {
        this.taskDao = taskDao;
        this.userDao = userDao;
        this.teamDao = teamDao;
    }

    @GetMapping("/task/create")
    public String createTask(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userDao.findAll());
        return "tasks/create-task";
    }

    @PostMapping("/task/create")
    public String saveTask(@ModelAttribute Task task, @RequestParam(name = "userId") long userId) {
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
