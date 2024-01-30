package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NotesService notesService;
    private UserService userService;
    private CredentialsService credentialsService;
    private FileService fileService;

    public HomeController(NotesService notesService, UserService userService, CredentialsService credentialsService, FileService fileService){
        this.userService = userService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomePage(Notes notes, Files files, Credentials credentials, Model model){
        Integer loggedUserId = userService.getCurrentUserId();
        if(null != loggedUserId){
            model.addAttribute("noteslist", this.notesService.getNotes(loggedUserId));
            model.addAttribute("credentiallist", this.credentialsService.getCredentials(loggedUserId));
            model.addAttribute("fileslist", this.fileService.getFiles(loggedUserId));
            return "home";
        }
        return "redirect:/login";
    }

}
