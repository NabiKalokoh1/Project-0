package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;

public class UserController {
    UserService userService = new UserService();

    public void handleCreateUser(Context ctx){
        User newUser = ctx.bodyAsClass(User.class);
        boolean success = userService.createUser(newUser);

        if (success){
            ctx.status(201);
        } else{
            ctx.status(400);
        }
    }
}
