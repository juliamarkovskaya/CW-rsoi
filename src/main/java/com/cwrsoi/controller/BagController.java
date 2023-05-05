package com.cwrsoi.controller;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.UserRepository;
import com.cwrsoi.service.BagService;
import com.cwrsoi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller

public class BagController {

    @Autowired
    private BagService bagService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email=p.getName();
        UserDtls user = userRepo.findByEmail(email);
        m.addAttribute("user", user);
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

    /*@GetMapping("bag")
    public String showBag(Model m, Principal p, UserDtls user,
                          @AuthenticationPrincipal Authentication authentication) {
        String email = p.getName();
        List<Bag> bagItems = bagService.getBagItemsByUser(user);

        m.addAttribute("bagItems", bagItems);

        return "user/bag";
    }*/

}
