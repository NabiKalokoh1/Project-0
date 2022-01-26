package com.revature.models;


public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private double checking = 0.0;
    private double savings = 0.0;
    private Type type;

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
    public void deposit(User user, int amount, int balance){
        //input where to deposit and how much
        //must be User
        //can't be a negative number (validation should be done before entering the method for all cases, yeah?
        //have a validation message
        if (balance == 1){
            user.checking += amount;
            System.out.println("You have deposited $" + amount + " into your checking account.");
        }
        if (balance == 2){
            user.savings += amount;
            System.out.println("You have deposited $" + amount + " into your savings account.");
        }


    }

    public void withdrawl(User user, int amount, int balance){
        //input where to withdraw and how much
        //must be User
        //can't be a negative number
        //can't take more than what's available
        //have a validation message
        if (balance == 1){
            if (user.checking < amount){
                System.out.println("You don't have enough money in this account.");
                return;
            }
            user.checking -= amount;
            System.out.println("You have withdrawn $" + amount + " from your checking account.");
        }
        if (balance == 2){
            if (user.savings < amount){
                System.out.println("You don't have enough money in this account.");
                return;
            }
            user.savings -= amount;
            System.out.println("You have withdrawn $" + amount + " from your savings account.");
        }
    }

    public void transfer(User user, int amount, int order){
        //input both checking and savings, a flag for which to take/give to, and how much
        //must be user
        //can't be a negative number
        //can't take more than what's available
        //have a validation message
        if (order == 1){
            //checking to savings
            user.withdrawl(user, amount, 1);
            user.deposit(user, amount, 2);
        }

        if (order == 2){
            //savings to checking
            user.withdrawl(user, amount, 2);
            user.deposit(user, amount, 1);
        }
    }

    public String accountInfo(User user){
        return "Account Information\n" +
                user.firstName + " " + user.lastName + "\n" +
                "E-mail: " + user.email + "\n" +
                "Username: " + user.username + "\n" +
                "Password: " + user.password + "\n" +
                "Checking Balance: " + user.checking + "\n" +
                "Savings Balance: " + user.savings;
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
