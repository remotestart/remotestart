package com.capstone.remotestart.controllers;

import com.capstone.remotestart.models.ConfirmationToken;
import com.capstone.remotestart.models.PasswordResetToken;
import com.capstone.remotestart.models.User;
import com.capstone.remotestart.repositories.ConfirmationTokenRepository;
import com.capstone.remotestart.repositories.PasswordResetTokenRepository;
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
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationTokenRepository confirmationTokenRepository, PasswordResetTokenRepository passwordResetTokenRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/sign-up")
    public ModelAndView showSignupForm(ModelAndView modelAndView, User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration/sign-up");
        return modelAndView;
    }

    @GetMapping("/reset-password")
    public String showPasswordResetPage(){
        return "password-reset";
    }

    @PostMapping("/reset-password")
    public ModelAndView sendPasswordResetEmail(ModelAndView modelAndView, @RequestParam(name = "username") String username){
        User user = userRepository.findByUsername(username);
        PasswordResetToken passwordResetToken = new PasswordResetToken(user);

        passwordResetTokenRepository.save(passwordResetToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("RESET YOUR PASSWORD!");
        mailMessage.setFrom("admin@remote-start.io");
        mailMessage.setText("To reset your password, please click here : "
                +"https://remote-start.io/change-password/"+passwordResetToken.getPasswordResetToken());

        emailSenderService.sendEmail(mailMessage);

        modelAndView.addObject("email", user.getEmail());

        modelAndView.setViewName("password-confirm-email");
        return modelAndView;
    }

    @RequestMapping(value="/change-password/{token}", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmChangePassword(ModelAndView modelAndView, @PathVariable String token)
    {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByPasswordResetToken(token);

        if(token != null)
        {
            User user = userRepository.findByUsername(passwordResetToken.getUser().getUsername());
            modelAndView.addObject("user", user);
            modelAndView.setViewName("change-password");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("registration/account-error");
        }

        return modelAndView;
    }

    @PostMapping("/sign-up")
    public ModelAndView saveUser(ModelAndView modelAndView, User user, @RequestParam(name = "confirm-password") String confirmPassword){

        //checking to see if password match
        if(!user.getPassword().equals(confirmPassword)) {
            modelAndView.addObject("error", true);
            modelAndView.setViewName("registration/sign-up");
            return modelAndView;
        }


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
            User user = userRepository.findByUsername(confirmationToken.getUser().getUsername());
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
