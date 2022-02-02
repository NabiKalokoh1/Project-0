package com.revature.daos;

import com.revature.models.User;
import java.util.List;


public interface UserDao {
    //delete these comments later (only attempted with one person so far)
    public boolean createUser(User user); //works
    public boolean deposit(User user, int id, int account);
    public boolean withdraw(User user, int id, int account);
    public boolean transfer(User user, int id, int order);
    public User getByUserAndPass(String email, String pass); //works but not really (user keeps returning null)
    public User getByUserId(int id); //works
    public List<User> getAll(); //works
    public boolean updateUser(User user); //works
    public boolean deleteUser(User user, int id);
}
