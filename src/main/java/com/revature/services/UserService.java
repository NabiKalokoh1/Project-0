package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpli;
import com.revature.models.User;
import com.revature.models.Type;
import java.util.List;

import java.util.Locale;

public class UserService {
    //crud stuff?
    private UserDao userDao = new UserDaoImpli();

    public boolean createUser(Type type, String firstName, String lastName, String email, String password){
        email = email.toLowerCase();
        User user = new User(type, firstName, lastName, email, password);
        return userDao.createUser(user);
    }

    public boolean createUser(User user){
        user.setEmail(user.getEmail().toLowerCase());
        return userDao.createUser(user);
    }

    public List<User> getAll(){
        return userDao.getAll();
    }

    public User getByUserId(int id){
        return userDao.getByUserId(id);
    }

    public boolean update(User user){
        return userDao.updateUser(user);
    }

    public User getByUserAndPass(String email, String pass){
        return userDao.getByUserAndPass(email, pass);
    }
}
