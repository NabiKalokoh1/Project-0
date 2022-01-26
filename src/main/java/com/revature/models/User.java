package com.revature.models;

import com.revature.models.Type;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private double checking = 0.0;
    private double savings = 0.0;
    private Type type;
    //will have stuff for deposit checking and savings and stuff
    //wont need to be inputted when constructed
    //account view and all account access can be inherited

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, String password, Type type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    //will probably move these somewhere else
    public void deposit(){
        //input where to deposit and how much
        //must be User
        //can't be a negative number
        //have a validation message

    }

    public void withdrawl(){
        //input where to withdraw and how much
        //must be User
        //can't be a negative number
        //can't take more than what's available
        //have a validation message

    }

    public void transfer(){
        //input both checking and savings, a flag for which to take/give to, and how much
        //must be user
        //can't be a negative number
        //can't take more than what's available
        //have a validation message
    }

    public void viewAccount(){
        //must be an employee or admin
        //input could be via username?
        //prints out everything in the User class
    }

    //should i made admin versions of deposit and what not?
    //i dont think so?

    //getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setGender(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getChecking() {
        return checking;
    }

    public void setChecking(double checking) {
        this.checking = checking;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
