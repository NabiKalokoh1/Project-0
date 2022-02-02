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

    public boolean delete(User user, int id){
        return userDao.deleteUser(user, id);
    }

    public User getByUserAndPass(String email, String pass){
        return userDao.getByUserAndPass(email, pass);
    }

    public boolean deposit(User user, int id, double amount, int account){
            //input where to deposit and how much
            //must be User
            //can't be a negative number (validation should be done before entering the method for all cases, yeah?
            //have a validation message
            if (amount < 0){
                System.out.println("Invalid amount.");
                return false;
            }
            if (account == 1){
                user.setChecking(user.getChecking() + amount);
                System.out.println("You have deposited $" + amount + " into your checking account.");
            }
            if (account == 2){
                user.setSavings(user.getSavings() + amount);
                System.out.println("You have deposited $" + amount + " into your savings account.");
            }

            return userDao.deposit(user, id, account);

        }

    public boolean withdraw(User user, int id, double amount, int balance){
        //input where to withdraw and how much
        //must be User or Admin
        //can't be a negative number
        //can't take more than what's available
        //have a validation message
        if (balance == 1){
            if (user.getChecking() < amount){
                System.out.println("You don't have enough money in this account.");
                return false;
            }
            user.setChecking(user.getChecking() - amount);
            System.out.println("You have withdrawn $" + amount + " from your checking account.");
        }
        if (balance == 2){
            if (user.getSavings() < amount){
                System.out.println("You don't have enough money in this account.");
                return false;
            }
            user.setSavings(user.getSavings() - amount);
            System.out.println("You have withdrawn $" + amount + " from your savings account.");
        }

        return userDao.withdraw(user, id, balance);
    }

    public boolean transfer(User user, int id, double amount, int order){
        //input both checking and savings, a flag for which to take/give to, and how much
        //must be user or admin
        //can't be a negative number
        //can't take more than what's available
        //have a validation message
        if (order == 1){
            //checking to savings
            boolean check = this.withdraw(user, id, amount, 1);
            if (!check){
                return false;
            }
            this.deposit(user, id, amount, 2);
        }

        if (order == 2){
            //savings to checking
            boolean check = this.withdraw(user, id, amount, 2);
            if (!check){
                return false;
            }
            this.deposit(user, id, amount, 1);
        }
        return userDao.transfer(user, id, order);
    }
}
