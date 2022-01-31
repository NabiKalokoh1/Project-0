package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;

import java.util.List;

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

    public void handleOneUser(Context ctx){
        //only Employee and Admin
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        User user = userService.getByUserId(id);
        ctx.json(user);
    }

    public void handleAllUsers(Context ctx){
        //only Employee and Admin
        List<User> user = userService.getAll();
        ctx.json(user);
    }

    public void handleUpdateUser(Context ctx){
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        User userUpdate = ctx.bodyAsClass(User.class);
        userUpdate.setUserId(id);
        boolean success = userService.update(userUpdate);

        if (success){
            ctx.status(201);
        } else{
            ctx.status(400);
        }
    }

    public void handleDeleteUser(Context ctx){
        //only Admins can do this
        ctx.status(404);
    }
}
