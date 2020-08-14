package com.capstone.remotestart.Controllers;

import com.capstone.remotestart.models.Task;
import com.capstone.remotestart.repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    private TaskRepository taskDao;

    public TaskController(TaskRepository taskDao) {this.taskDao = taskDao;}

    @GetMapping("/task/create")
    public String createTask(Model model){
        model.addAttribute("task", new Task());
        return "tasks/create-task";
    }

    @PostMapping("/task/create")
    public String saveTask(@ModelAttribute Task task){
        taskDao.save(task);
        return "redirect:/task";
    }

    @GetMapping("/task")
    private String showTask() {
        return "tasks/task";
    }
}
