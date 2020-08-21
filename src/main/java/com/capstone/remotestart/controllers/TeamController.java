package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.Role;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeamController {

    //dependency injection
    private TeamRepository teamDao;
    private UserTeamRoleRepository userTeamRoleDao;
    private RoleRepository roleDao;
    private UserRepository userDao;
    private ProjectRepository projectDao;

    @Autowired
    private EmailSenderService emailSenderService;

    public TeamController(TeamRepository teamDao, UserTeamRoleRepository userTeamRoleDao, RoleRepository roleDao, UserRepository userDao, ProjectRepository projectDao) {
        this.teamDao = teamDao;
        this.userTeamRoleDao = userTeamRoleDao;
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.projectDao = projectDao;
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
        return "redirect:teams";
    }

    @GetMapping("/teams")
    private String showTeamPage(Model model){
        model.addAttribute("teams", teamDao.findAll());
        return "teams/teams";
    }

    @GetMapping("/team/{id}")
    private String teamPage(Model model, @PathVariable long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("role", userDao.checkIfTeamLeader(user.getId(), id));
        model.addAttribute("team", teamDao.getOne(id));
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("projects", projectDao.findAllByTeamId(id));

        if (userDao.checkIfOnTeam(user.getId(), id) == null) {
            return "redirect:teams";
        } else {
            List<Long> userIdList = userDao.allUsersByTeamId(id);
            List<User> userList = new ArrayList<>();

            for (int i = 0; i < userIdList.size(); i++) {
                userList.add(userDao.getOne(userIdList.get(i)));
            }

            model.addAttribute("teamUsers", userList);
            return "teams/team";
        }
    }

    @GetMapping("/team/request/{id}")
    private String requestToJoinTeam(Model model, @PathVariable long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("role", userDao.checkIfTeamLeader(user.getId(), id));
        model.addAttribute("team", teamDao.getOne(id));

        if (userDao.checkIfOnTeam(user.getId(), id) != null) {
            return "redirect:/teams";
        } else {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userDao.getOne(userDao.findTeamLeaderByTeamId(id)).getEmail());
            mailMessage.setSubject(user.getFirstName() + " " + user.getLastName() + " would like to join your team!");
            mailMessage.setFrom("admin@remote-start.io");
            mailMessage.setText("To view your team page and add them, please click here : "
                    +"https://remote-start.io/team/" + id);

            emailSenderService.sendEmail(mailMessage);

            model.addAttribute("teamName", teamDao.getOne(id).getName());
            return "teams/request-to-join";
        }
    }

    @GetMapping("/team/{id}/add/{userId}")
    private String addUserToTeam(@PathVariable long id, @PathVariable long userId){
        //grabbing user by id
        User user = userDao.getOne(userId);
        //grabbing team by id
        Team team = teamDao.getOne(id);

        //new mapping table object
        UserTeamRoleLink newMapping = new UserTeamRoleLink();

        if (userDao.checkIfTeamLeader(user.getId(), id) != 1) {
            return "redirect:teams";
        } else {
            //using setters to set user and team to table object
            newMapping.setUser(user);
            newMapping.setTeam(team);
            newMapping.setRole(roleDao.getOne(2L));

            //saving table object to db
            userTeamRoleDao.save(newMapping);

            return "redirect:team/" + id;
        }
    }

    @GetMapping("/teams/my-teams")
    private String viewMyTeams(Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Long> teamIdList = teamDao.allTeamsByUserId(user.getId());
        List<Team> teamList = new ArrayList<>();

        for(int i = 0; i < teamIdList.size(); i++){
            teamList.add(teamDao.getOne(teamIdList.get(i)));
        }

        model.addAttribute("teams", teamList);
        return "teams/view-my-teams";
    }
}