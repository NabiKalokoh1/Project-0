package com.revature.models;

import com.revature.models.User;
import com.revature.models.Type;

public class BankDriver {
    public static void main(){
        //welcome to Bank, what would you like to do? (1. Register; 2. Log in)
        //(If 1) ask the user to put in their full name, email, type of user, create a username + password then have them sign back in
        //(If 2) then ask for username and password

        //once logged in
        //(if consumer) ask if they would like to deposit, withdraw, or transfer. give user, valid amount, and which account
        //(if employee) ask which account they would like to view by putting in username? or maybe email instead to grab the full user
        //(if admin) do the same as employee, but with the ability to do what a User can and then some
        //not sure how to do approve/deny
        //cancelAccount?

        //log out will just end


        User a = new User("nabiatu","kalokoh","nabikalokoh@gmail.com","nabi1", "ffff", Type.CONSUMER);

        a.accountInfo(a);
    }
}
