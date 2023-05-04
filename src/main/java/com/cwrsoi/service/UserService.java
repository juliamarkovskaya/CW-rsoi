package com.cwrsoi.service;

import com.cwrsoi.model.UserDtls;

public interface UserService {

    public UserDtls createUser(UserDtls user);

    UserDtls editUser(UserDtls user);

    public boolean checkEmail(String email);

    public String deleteUser(String email);
}
