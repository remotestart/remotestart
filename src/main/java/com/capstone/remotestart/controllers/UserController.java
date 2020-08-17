package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.ConfirmationToken;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.repositories.ConfirmationTokenRepository;
import com.capstone.remotestart.repositories.UserRepository;
import com.capstone.remotestart.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/sign-up")
    public ModelAndView showSignupForm(ModelAndView modelAndView, User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration/sign-up");
        return modelAndView;
    }

    @PostMapping("/sign-up")
    public ModelAndView saveUser(ModelAndView modelAndView, User user){
        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if(existingUser != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("registration/account-error");
        }
        else
        {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("admin@remote-start.io");
            mailMessage.setText("To confirm your account, please click here : "
                    +"https://remote-start.io/confirm-account/"+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("email", user.getEmail());

            modelAndView.setViewName("registration/successful-registration");
        }

        return modelAndView;
    }

    @RequestMapping(value="/confirm-account/{token}", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @PathVariable String token)
    {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(confirmationToken.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("registration/confirm-account");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("registration/account-error");
        }

        return modelAndView;
    }
}
