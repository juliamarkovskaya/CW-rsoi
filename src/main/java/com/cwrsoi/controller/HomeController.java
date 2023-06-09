package com.cwrsoi.controller;

import com.cwrsoi.model.BookDtls;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.UserRepository;
import com.cwrsoi.service.BookService;
import com.cwrsoi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private BookService bookService;

    @ModelAttribute
    private void userDetails(Model m, Principal p) {

        if(p != null) {
            String email=p.getName();
            UserDtls user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }

    }

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<BookDtls> books = bookService.getAllBooks();
        model.addAttribute("books", books);
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
        //return "redirect:/signin";
    }

    @GetMapping("/loadForgotPassword")
    public String loadForgotPassword() {
        return "forgot_password";
    }

    @GetMapping("/loadResetPassword/{id}")
    public String loadResetPassword(@PathVariable int id, Model m) {
        m.addAttribute("id", id);
        return "reset_password";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassword(@RequestParam String email,
                                 @RequestParam String mobileNum, HttpSession session) {

        UserDtls user = userRepo.findByEmailAndAndMobileNumber(email, mobileNum);

        if(user != null) {
            return "redirect:/loadResetPassword/" + user.getId();
        } else {
            session.setAttribute("msg", "invalid email & mobile number");
            return "forgot_password";
        }

    }

    @PostMapping("/changePassword")
    public String resetPassword(@RequestParam String psw, @RequestParam Integer id, HttpSession session) {

        UserDtls user = userRepo.findById(id).get();

        String encryptPsw = passwordEncoder.encode(psw);
        user.setPassword(encryptPsw);

        UserDtls updateUser = userRepo.save(user);

        if(updateUser != null) {
            session.setAttribute("msg", "Password change successfully");
        }

        return "redirect:/loadForgotPassword";
    }

    @GetMapping ("/editProfile")
    public String loadEditProfile() {
        return "edit_profile";
    }
    @PostMapping("/updateProfile")
    public String editProfile(Model model, Principal p, @RequestParam String firstName, @RequestParam String lastName,
                              @RequestParam String password, HttpSession session, String mobileNumber) {

        String email = p.getName();
        UserDtls user = userRepo.findByEmail(email);

        boolean f = passwordEncoder.matches(password, user.getPassword());

        if(f) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMobileNumber(mobileNumber);

            userRepo.save(user);
            model.addAttribute("user", user);
            session.setAttribute("msg", "Changes are saved.");

        } else {
            session.setAttribute("msg", "Wrong password.");
        }
        return "redirect:/editProfile";
    }


}
