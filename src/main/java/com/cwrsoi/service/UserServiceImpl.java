package com.cwrsoi.service;

import com.cwrsoi.config.CustomUserDetails;
import com.cwrsoi.config.UserDetailsServiceImpl;
import com.cwrsoi.model.Bag;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.BagRepository;
import com.cwrsoi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Autowired
    private BagRepository bagRepo;

    @Override
    public UserDtls createUser(UserDtls user) {

        user.setPassword(passwordEncode.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        return userRepo.save(user);
    }

    @Override
    public UserDtls editUser(UserDtls user){
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setMobileNumber(user.getMobileNumber());
        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public String deleteUser(String email) {
        UserDtls user = userRepo.findByEmail(email);

        if(user!=null) {
            List<Bag> bagItems = bagRepo.findByUser(user);
            for (Bag bag : bagItems) {
                bagRepo.delete(bag);
            }
            //Bag bagItems = bagRepo.getAllByUser(user);
            //for(int i= 0; i <bagItems; i++) {
            //if (bagItems != null) {
                //bagRepo.delete(bagItems); //}
            //} else {
                userRepo.delete(user);
                return "User Delete Successfully";
            //}
        }
        return "Something wrong";
    }

    public UserDtls getUserById(Integer id) {
        UserDtls user = userRepo.findById(id).get();
        return user;
    }

    /*public UserDtls getCurrentlyLoggedInUser(String email, Principal p) {
        UserDtls user = userRepo.findByEmail(email);
        email = p.getName();
        return user;
    }*/


    /*@Override
    public UserDtls getCurrentlyLoggedInUser(Authentication authentication) {
        if (authentication == null) { return null; }

        UserDtls user = null;
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails) {
            user = ((CustomUserDetails) principal).getUsername();
        } else if (principal instanceof UserDetailsServiceImpl) {
            String email = ((UserDetailsServiceImpl) principal).getEmail();
            user = getUserByEmail(email);
        }
        return user;
    }*/
}
