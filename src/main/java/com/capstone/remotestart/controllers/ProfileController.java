package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.User;
import com.capstone.remotestart.repositories.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private UserRepository userDao;

    public ProfileController(UserRepository userDao){
        this.userDao = userDao;
    }


    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDao.getOne(user.getId()));
        System.out.println(user.getFirstName());
        return "profile";
    }

    @GetMapping("/profile/{id}/edit-profile")
    public String editProfileInfo(Model model, @PathVariable Long id){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("id", id);
        model.addAttribute("user", userDao.getOne(id));
        if (loggedInUser.getId() != id ){
            return "redirect:/profile";
        } else {
            return "edit-profile";
        }

    }

    @PostMapping("/profile/{id}/edit-profile")
    public String editProfile(@PathVariable Long id, @ModelAttribute User user){

            userDao.editProfileInfo(id, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());

        return "redirect:/profile";
    }

}
