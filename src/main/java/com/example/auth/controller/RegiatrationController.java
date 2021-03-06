package com.example.auth.controller;

import com.example.auth.domain.User;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegiatrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("text", "Add new user");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){

        if (!userService.addUser(user)){
            model.put("message", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivate = userService.activateUser(code);

        if (isActivate){
            model.addAttribute("message", "User sucessfully activated");
        }else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}
