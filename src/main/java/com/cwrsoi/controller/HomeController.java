package com.cwrsoi.controller;

import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.UserRepository;
import com.cwrsoi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    private void userDetails(Model m, Principal p) {

        if(p != null) {
            String email=p.getName();
            UserDtls user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }

    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/signin")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping("/createUser")
    public String createuser(@ModelAttribute UserDtls user, HttpSession session) {

       // System.out.println(user);

        boolean f = userService.checkEmail(user.getEmail());

        if (f) {
            session.setAttribute("msg", "Email Id already exists");
        }
        else {
            UserDtls userDtls = userService.createUser(user);
            if (userDtls != null)
            {
                session.setAttribute("msg", "Register successfully");
            } else {
                session.setAttribute("msg", "Something wrong on server");
            }
        }

        return "redirect:/register";
    }

    @GetMapping("/loadForgotPassword")
    public String loadForgotPassword() {
        return "forgot_password";
    }

    @GetMapping("/loadResetPassword")
    public String loadResetPassword() {
        return "reset_password";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassword() {


        return "";
    }


}
