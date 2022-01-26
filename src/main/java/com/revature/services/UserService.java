package com.revature.services;

import com.revature.models.User;
import com.revature.models.Type;

//import java.util.locale;

public class UserService {
    //crud stuff?

    public User createUser(String firstName, String lastName, String email, String username, String password, Type type){
        User u = new User(firstName, lastName, email, username, password, type);
        return u;
    }
}
