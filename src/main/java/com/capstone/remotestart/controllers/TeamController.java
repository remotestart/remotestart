package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.repositories.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {

    //dependency injection
    private TeamRepository teamDao;

    public TeamController(TeamRepository teamDao) {
        this.teamDao = teamDao;
    }

    @GetMapping("/team/create")
    public String createTeam(Model model){
        model.addAttribute("team", new Team());
        return "create-team";
    }
}
