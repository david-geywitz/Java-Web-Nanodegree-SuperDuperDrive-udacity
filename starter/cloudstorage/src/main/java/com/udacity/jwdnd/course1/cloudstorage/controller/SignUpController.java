package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView(){
        return "signup";
    }

    @PostMapping
    public String userSignUp(@ModelAttribute Users user, Model model){
        String signupError = null;

        if(!userService.isUsernameAvailable(user.getUsername())){
            signupError = "Username already exists";
        }

        if(signupError == null){
            int rowsAdded = userService.createUser(user);
            if(rowsAdded < 0){
                signupError = "There was error in signing you up. Please try again.";
            }
        }

        if(signupError == null){
            model.addAttribute("signupSuccess", true);
            return "login";
        } else {
            model.addAttribute("signupError", true);
        }

        return "signup";
    }
}
