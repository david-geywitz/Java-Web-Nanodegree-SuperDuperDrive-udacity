package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;


@Controller
public class CredentialController {

    private CredentialsService credentialsService;
    private UserService userService;

    public CredentialController(CredentialsService credentialsService, UserService userService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @PostMapping("/addcredential")
    public String saveCredentials(Credentials credentials, Model model){
        Integer loggedUserId = userService.getCurrentUserId();
        if(null != credentials && null != loggedUserId){
            if((null != credentials.getCredentialId()) && (0 != this.credentialsService.updateCredentials(credentials))){
                model.addAttribute("result","success");
            } else if (0 != this.credentialsService.addCredentials(credentials,loggedUserId)){
                model.addAttribute("result","success");
            }
            return "result";
        }
        return "redirect:/login";
    }

    @GetMapping("/deletecredential/{credentialId}")
    public String deleteCredentials(@PathVariable Integer credentialId, Model model){
        Integer loggedUserId = userService.getCurrentUserId();
        if(null != loggedUserId && this.credentialsService.deleteCredentials(credentialId)){
            model.addAttribute("result","success");
        }
        return "result";
    }
}
