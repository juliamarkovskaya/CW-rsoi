package com.cwrsoi.controller;

import com.cwrsoi.model.BookDtls;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.UserRepository;
import com.cwrsoi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private BookService bookService;

    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email=p.getName();
        UserDtls user = userRepo.findByEmail(email);
        m.addAttribute("user", user);
    }

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

    @GetMapping("/bag")
    public String bag() {
        return "user/bag";
    }
    @GetMapping("/bag/{idBook}")
    public String addToBag(@PathVariable Integer idBook, Model model) {
        //BookDtls book = bookService.getBookById()
        return "user/bag";
    }

    @GetMapping("/changePass")
    public String loadChangePassword() {
        return "user/change_password";
    }

    @PostMapping("/updatePassword")
    public String changePassword(Principal p, @RequestParam("oldPass") String oldPass,
                                 @RequestParam("newPass") String newPass, HttpSession session) {

       String email = p.getName();
       UserDtls loginUser = userRepo.findByEmail(email);

       boolean f = passwordEncoder.matches(oldPass, loginUser.getPassword());

       if(f) {
           loginUser.setPassword(passwordEncoder.encode(newPass));
           UserDtls updatePasswordUser = userRepo.save(loginUser);

           if(updatePasswordUser != null) {
               session.setAttribute("msg", "Password Change Success");
           } else {
               session.setAttribute("msg", "Something wrong on server");
           }

       } else {
           session.setAttribute("msg", "Old password incorrect");
       }

        return "redirect:/user/changePass";
    }

}
