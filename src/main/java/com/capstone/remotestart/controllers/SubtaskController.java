package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Project;
import com.capstone.remotestart.models.Subtask;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.repositories.ProjectRepository;
import com.capstone.remotestart.repositories.SubtaskRepository;
import com.capstone.remotestart.repositories.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubtaskController {

    private SubtaskRepository subtaskDao;
    private TaskRepository taskDao;
    private ProjectRepository projectDao;

    public SubtaskController(SubtaskRepository subtaskDao, TaskRepository taskDao, ProjectRepository projectDao) {
        this.subtaskDao = subtaskDao;
        this.taskDao = taskDao;
        this.projectDao = projectDao;
    }

    @GetMapping("/subtask/create/{projectId}/{taskId}")
    public String createSubtaskForm(Model model, @PathVariable long taskId, @PathVariable long projectId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("project", projectDao.getOne(projectId));
        model.addAttribute("subtask", new Subtask());
        model.addAttribute("task", taskDao.getOne(taskId));

        if (taskDao.findUserByTaskId(taskId) != user.getId()) {
            return "redirect:/teams";
        } else {
            return "subtasks/create-subtask";
        }
    }

    @PostMapping("/subtask/create/{projectId}/{taskId}")
    public String saveSubtask(@ModelAttribute Subtask subtask, @PathVariable long taskId, @PathVariable long projectId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        subtask.setTask(taskDao.getOne(taskId));
        subtaskDao.save(subtask);
        return "redirect:/project/" + projectId + "/" + user.getId();
    }
}
