package com.cwrsoi.controller;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.UserRepository;
import com.cwrsoi.service.BagService;
import com.cwrsoi.service.BookService;
import com.cwrsoi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BagService bagService;

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

    @GetMapping("/bag/{id}")
    public String addToBag(Principal p, @PathVariable Integer id, Model model) {

        String email = p.getName();
        UserDtls user = userRepo.findByEmail(email);
        List<Bag> bagItems = bagService.getBagItemsByUser(user);
        model.addAttribute("bagItems", bagItems);
        return "user/bag";
    }

    /*@GetMapping("/bag")
    public String bag() {
        return "user/bag";
    }

    @GetMapping("/bag/{id}")
    public String addToBag(@PathVariable Integer id, Model model) {
        //BookDtls book = bookService.getBookById()
        return "user/bag";
    }*/

    /*@GetMapping("/bag/{idBook}")
    public String addToBag(@PathVariable Integer id, Model m, Principal p, UserDtls user,
                           @AuthenticationPrincipal Authentication authentication) {
        //BookDtls book = bookService.getBookById()
            String email = p.getName();
            List<Bag> bagItems = bagService.getBagItemsByUser(user);

            m.addAttribute("bagItems", bagItems);
        return "user/bag";
    }*/

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

    @GetMapping("/deleteUser")
    public String deleteUser() {

        return "user/delete";
    }

    @PostMapping("/deleteUserAccount")
    public String deleteUserAccount(Principal p, @RequestParam String password,
                                    HttpSession session) {
        String email = p.getName();
        UserDtls user = userRepo.findByEmail(email);

        boolean f = passwordEncoder.matches(password, user.getPassword());

        if(f){
            userService.deleteUser(email);
            session.setAttribute("msg", user);
            session.invalidate();
            System.out.println("User Account " + email + " is DELETED!");
        } else {
            session.setAttribute("msg", "Wrong password.");
        }

        return  "redirect:/";
    }



}
