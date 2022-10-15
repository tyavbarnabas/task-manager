package com.eventmanager.controller;

import com.eventmanager.model.User;
import com.eventmanager.repository.UserRepository;
import com.eventmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;
//    @GetMapping("/")
//    public String viewHomePage(){
//        return "index";
//    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("invalid",null);
        model.addAttribute("user ",new User());

        return "register";
    }

    @PostMapping("/reg")
    public String processRegistration(@ModelAttribute("user") User user,Model model){
        Optional<User> user1 = iUserService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if(!user1.isEmpty()){
            model.addAttribute("user","User has already been registered");
            return "register";
        }
        if(user1.isEmpty()){
            model.addAttribute("user","signup successfully");
        }
        iUserService.saveUser(user);
        return "redirect:/login";

    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }
}
