package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.models.UserTeamRoleLink;
import com.capstone.remotestart.repositories.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {

    //dependency injection
    private TeamRepository teamDao;
    private UserTeamRoleLink userTeamRoleDao;

    public TeamController(TeamRepository teamDao, UserTeamRoleLink userTeamRoleDao) {
        this.teamDao = teamDao;
        this.userTeamRoleDao = userTeamRoleDao;
    }

    @GetMapping("/team/create")
    public String createTeam(Model model){
        model.addAttribute("team", new Team());
        return "teams/create-team";
    }

    @PostMapping("/team/create")
    public String saveTeam(@ModelAttribute Team team){
        UserTeamRoleLink newMapping = new UserTeamRoleLink();
        teamDao.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/teams")
    private String showTeamPage(Model model){
        model.addAttribute("teams", teamDao.findAll());
        return "teams/teams";
    }

    @GetMapping("/team/{id}")
    private String teamPage(Model model, @PathVariable long id){
        model.addAttribute("team", teamDao.getOne(id));
        return "teams/team";
    }




}
