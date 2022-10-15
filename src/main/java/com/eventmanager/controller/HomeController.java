package com.eventmanager.controller;

import com.eventmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    IUserService iUserService;
    @GetMapping
    public String viewHomePage(Model model, HttpSession session){
        if (session.getAttribute("user") == null) return "redirect:/login";
        return "home";
    }
}
