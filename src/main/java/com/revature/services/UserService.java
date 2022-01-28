package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpli;
import com.revature.models.User;
import com.revature.models.Type;

import java.util.Locale;

public class UserService {
    //crud stuff?
    private UserDao userDao = new UserDaoImpli();

    public boolean createUser(Type type, String firstName, String lastName, String email, String username, String password){
        email = email.toLowerCase();
        User user = new User(type, firstName, lastName, email, username, password);
        return userDao.createUser(user);
    }
}
