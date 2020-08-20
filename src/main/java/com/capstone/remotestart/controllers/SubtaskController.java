package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Subtask;
import com.capstone.remotestart.models.User;
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

    public SubtaskController(SubtaskRepository subtaskDao, TaskRepository taskDao) {
        this.subtaskDao = subtaskDao;
        this.taskDao = taskDao;
    }

    @GetMapping("/subtask/create/{taskId}")
    public String createSubtaskForm(Model model, @PathVariable long taskId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("subtask", new Subtask());
        model.addAttribute("task", taskDao.getOne(taskId));

        if (taskDao.findUserByTaskId(taskId) != user.getId()) {
            return "redirect:teams";
        } else {
            return "subtasks/create-subtask";
        }
    }

    @PostMapping("/subtask/create/{taskId}")
    public String saveSubtask(@ModelAttribute Subtask subtask, @PathVariable long taskId){
        subtask.setTask(taskDao.getOne(taskId));
        subtaskDao.save(subtask);
        return "redirect:task";
    }
}
