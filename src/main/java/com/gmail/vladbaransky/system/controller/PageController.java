package com.gmail.vladbaransky.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PageController {

    @GetMapping("/list")
    public String getHomePage() {
        return "list_users";
    }

    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/403")
    public String accessDeniedPage() {
        return "access_denied_page";
    }
}
