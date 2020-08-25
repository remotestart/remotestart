package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Task;
import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.models.UserTeamRoleLink;
import com.capstone.remotestart.repositories.*;
import com.capstone.remotestart.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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
    private SubtaskRepository subtaskDao;
    @Autowired
    private EmailSenderService emailSenderService;


    public TaskController(TaskRepository taskDao, UserRepository userDao, TeamRepository teamDao, ProjectRepository projectDao, SubtaskRepository subtaskDao, EmailSenderService emailSenderService) {
        this.taskDao = taskDao;
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.projectDao = projectDao;
        this.subtaskDao = subtaskDao;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/task/create/{projectId}")
    public String createTask(Model model, @PathVariable long projectId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //getting all users that are currently on the team
        List<Long> userIdList = userDao.allUsersByTeamId(projectDao.teamIdFromProjectId(projectId));
        List<User> userList = new ArrayList<>();

        for(int i = 0; i < userIdList.size(); i++){
            userList.add(userDao.getOne(userIdList.get(i)));
        }

        model.addAttribute("project", projectDao.getOne(projectId));
        model.addAttribute("users", userList);
        model.addAttribute("projectId", projectId);
        model.addAttribute("task", new Task());

        if (userDao.checkIfTeamLeader(user.getId(), projectDao.teamIdFromProjectId(projectId)) != 1) {
            return "redirect:/teams";
        } else {
            return "tasks/create-task";
        }
    }

    @PostMapping("/task/create/{projectId}")
    public String saveTask(@ModelAttribute Task task, @RequestParam(name = "userId") long userId, @PathVariable long projectId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        task.setProject(projectDao.getOne(projectId));
        task.setUser(userDao.getOne(userId));
        taskDao.save(task);
        taskDao.editStateOfCompletion(1, task.getId());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userDao.getOne(userId).getEmail());
        mailMessage.setSubject("New task assignment");
        mailMessage.setFrom("admin@remote-start.io");
        mailMessage.setText(user.getFirstName() + " " + user.getLastName() + " has assigned a new task to you on project: " + projectDao.getOne(projectId).getName() + ". Click here to log in: https://remote-start.io/login");

        emailSenderService.sendEmail(mailMessage);


        return "redirect:/project/" + projectId;
    }

    @GetMapping("/task")
    private String showTask(Model model) {
        model.addAttribute("task", taskDao.findAll());
        return "tasks/task";
    }

    //show edit task form
    @GetMapping("/project/{projectId}/task/{taskId}/edit")
    private String showEditTaskPage(Model model, @PathVariable long taskId, @PathVariable long projectId){

        //getting all users that are currently on the team
        List<Long> userIdList = userDao.allUsersByTeamId(projectDao.teamIdFromProjectId(projectId));
        List<User> userList = new ArrayList<>();

        for(int i = 0; i < userIdList.size(); i++){
            userList.add(userDao.getOne(userIdList.get(i)));
        }

        //sending in users
        model.addAttribute("users", userList);
        //sending in project ID for post mapping redirect
        model.addAttribute("projectID", projectDao.getOne(projectId));
        //sending in task based on path variable task ID
        model.addAttribute("task", taskDao.getOne(taskId));
        return "tasks/edit-task";
    }

    //post mapping for edit task
    @PostMapping("/project/{projectId}/task/{taskId}/edit")
    private String editTask(@PathVariable long projectId, @PathVariable long taskId, @ModelAttribute Task task, @RequestParam(name = "userId") long userId){
        taskDao.editTaskById(taskId, task.getTitle(), task.getDescription(), userId);
        return "redirect:/project/" + projectId + "/all-tasks";
    }

    @PostMapping("/project/{projectId}/task/{taskId}/update-state")
    private String updateTaskState(@PathVariable long projectId, @PathVariable long taskId, @RequestParam(name = "task-state") long stateId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskDao.editStateOfCompletion(stateId, taskId);
        return "redirect:/project/" + projectId + "/" + user.getId();
    }

    @GetMapping("/project/{projectId}/task/{taskId}/delete")
    private String deleteTask(@PathVariable long taskId, @PathVariable long projectId){
        subtaskDao.deleteSubtasksFromTaskId(taskId);
        taskDao.deleteById(taskId);
        return "redirect:/project/" + projectId + "/all-tasks";
    }

}
