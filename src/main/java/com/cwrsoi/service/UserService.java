package com.cwrsoi.service;

import com.cwrsoi.model.UserDtls;
import org.springframework.security.core.Authentication;

public interface UserService {

    public UserDtls createUser(UserDtls user);

    UserDtls editUser(UserDtls user);

    public boolean checkEmail(String email);

    public String deleteUser(String email);

    public UserDtls getUserById(Integer id);
    //public UserDtls getCurrentlyLoggedInUser(Authentication authentication)

}