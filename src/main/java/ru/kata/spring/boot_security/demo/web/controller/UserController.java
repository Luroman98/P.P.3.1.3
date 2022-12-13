package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.web.service.UserServiceImp;

import java.security.Principal;


@Controller
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping("/user")
    public String printUsers(ModelMap model, Principal principal) {
        model.addAttribute("messages", userService.loadUserByUsername(principal.getName()).toString());
        return "user/user";
    }
}
