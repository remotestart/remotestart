package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Subtask;
import com.capstone.remotestart.repositories.SubtaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubtaskController {

    private SubtaskRepository subtaskDao;

    public SubtaskController(SubtaskRepository subtaskDao) {
        this.subtaskDao = subtaskDao;
    }

    @GetMapping("/subtask/create")
    public String createSubtaskForm(Model model){
        model.addAttribute("subtask", new Subtask());
        return "subtasks/create-subtask";
    }

    @PostMapping
    public String saveSubtask(@ModelAttribute Subtask subtask){
        subtaskDao.save(subtask);
        return "redirect:/task";
    }
}
