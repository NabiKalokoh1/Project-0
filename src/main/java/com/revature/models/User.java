package com.revature.models;


public class User {
    private int userId;
    private Type type;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double checking;
    private double savings;

    public User() {
    }

    public User(Type type, String firstName, String lastName, String email, String password) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(int userId, Type type, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public String toString(){
        return "Account Information\n" +
                "Full Name: " + this.firstName + " " + this.lastName + "\n" +
                "E-mail: " + this.email + "\n" +
                "Password: " + this.password + "\n" +
                "Checking Balance: " + this.checking + "\n" +
                "Savings Balance: " + this.savings;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
