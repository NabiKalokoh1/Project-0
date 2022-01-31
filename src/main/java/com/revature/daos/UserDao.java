package com.revature.daos;

import com.revature.models.User;
import java.util.List;


public interface UserDao {
    public boolean createUser(User user);
    public boolean deposit(User user, int amount, int account);
    public boolean withdraw(User user, int amount, int account);
    public boolean transfer(User user, int amount, int order);
    public User getByUserAndPass(String email, String pass);
    public User getByUserId(int id);
    public List<User> getAll();
    public boolean updateUser(User user);
}
