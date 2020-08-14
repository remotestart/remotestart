package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.repositories.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "teams/create-team";
    }

    @PostMapping("/team/create")
    public String saveTeam(@ModelAttribute Team team){
        teamDao.save(team);
        return "redirect:/team";
    }

    @GetMapping("/team")
    private String showTeamPage(){
        return "teams/team";
    }


}
