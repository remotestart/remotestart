package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Role;
import com.capstone.remotestart.models.Team;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.models.UserTeamRoleLink;
import com.capstone.remotestart.repositories.RoleRepository;
import com.capstone.remotestart.repositories.TeamRepository;
import com.capstone.remotestart.repositories.UserTeamRoleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserTeamRoleRepository userTeamRoleDao;
    private RoleRepository roleDao;

    public TeamController(TeamRepository teamDao, UserTeamRoleRepository userTeamRoleDao, RoleRepository roleDao) {
        this.teamDao = teamDao;
        this.userTeamRoleDao = userTeamRoleDao;
        this.roleDao = roleDao;
    }

    @GetMapping("/team/create")
    public String createTeam(Model model){
        model.addAttribute("team", new Team());
        return "teams/create-team";
    }

    @PostMapping("/team/create")
    public String saveTeam(@ModelAttribute Team team){
        //logged in user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //new mapping table object
        UserTeamRoleLink newMapping = new UserTeamRoleLink();


        //saving new team to db
        teamDao.save(team);

        //using setters to set logged in user and new team to table object
        newMapping.setUser(user);
        newMapping.setTeam(team);
        newMapping.setRole(roleDao.getOne(1L));
        //saving table object to db
        userTeamRoleDao.save(newMapping);
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