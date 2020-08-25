package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.User;
import com.capstone.remotestart.repositories.UserRepository;
import com.capstone.remotestart.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @Autowired
    private EmailSenderService emailSenderService;

    private UserRepository userDao;

    private PasswordEncoder passwordEncoder;

    public ProfileController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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

    @GetMapping("/profile/{id}/change-password")
    public String changePasswordEmail(Model model, @PathVariable Long id){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("id", id);
        model.addAttribute("user", userDao.getOne(id));
        if (loggedInUser.getId() != id ){
            return "redirect:/profile";
        } else {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(loggedInUser.getEmail());
            mailMessage.setSubject(loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " : " + "Remote-start Password Change");
            mailMessage.setFrom("admin@remote-start.io");
            mailMessage.setText("Please click this link to update/change your password : " +
                    "https://remote-start.io/profile/" + id + "/change-password-form");

            emailSenderService.sendEmail(mailMessage);

            return "email-password";
        }
    }

    @GetMapping("profile/{id}/change-password-form")
    public String changePassword(@PathVariable Long id, @ModelAttribute User user, Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("id", id);
        model.addAttribute("user", userDao.getOne(id));
        if (loggedInUser.getId() != id ){
            return "redirect:/profile";
        } else {
            return "change-password-form";
        }
    }

    @PostMapping("profile/{id}/change-password-form")
        public String saveNewPassword(@PathVariable Long id, @ModelAttribute User user){

        String hash = passwordEncoder.encode(user.getPassword());
//        userDao.getOne(id).setPassword(hash);
        userDao.editPassword(id, hash);

        return "redirect:/profile";
    }

    @PostMapping("/change-password-form")
    public String changedLoggedOutPassword(@ModelAttribute User user, @RequestParam(name = "password") String password){
        String hash = passwordEncoder.encode(password);
        userDao.editPassword(user.getId(), hash);
        return "redirect:/login";
    }

}
