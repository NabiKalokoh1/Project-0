package com.revature.daos;

import com.revature.models.User;


public interface UserDao {
    public boolean createUser(User user);
    public boolean deposit(User user, int amount, int account);
    public boolean withdraw(User user, int amount, int account);
    //public boolean transfer(User user, int amount, int order);
    public User getByUserAndPass(String email, String pass);
}
