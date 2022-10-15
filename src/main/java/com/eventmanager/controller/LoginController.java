package com.eventmanager.controller;

import com.eventmanager.model.User;
import com.eventmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;
@Controller
public class LoginController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("invalid", null);
        model.addAttribute("valid", "newRegistered");
        model.addAttribute("user", new User());

        return "login";
    }

    @PostMapping("/home")
    private String loginPage(HttpSession httpSession, User user, Model model){
        Optional<User> user1 = iUserService.getUserByEmailAndPassword(user.getEmail(),  user.getPassword());

        if (!user1.isPresent()){
            model.addAttribute("invalid", "try again");
            return "login";
        }
        if (!user1.isPresent()){
            model.addAttribute("invalid", "incorrect");
            return "login";
        }
        httpSession.setAttribute("user", user1);
        httpSession.setAttribute("id", user1.get().getId());
        return "redirect:/";

    }
}
