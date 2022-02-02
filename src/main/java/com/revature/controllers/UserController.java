package com.revature.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        User user = ctx.bodyAsClass(User.class);
        user.setUserId(id);
        boolean success = userService.delete(user, id);

        if (success){
            ctx.status(200);
        } else{
            ctx.status(404);
        }
    }

    //handle deposit, withdraw, transfer
    public boolean handleDeposit(Context ctx){
        ObjectMapper mapper = new ObjectMapper();
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        User user = userService.getByUserId(id);
        String accountPara = ctx.pathParam("account");
        int account = Integer.parseInt(accountPara);
        deposit d;
        try {
            d = mapper.readValue(ctx.body(),deposit.class);
            boolean success = userService.deposit(user, id, Double.parseDouble(d.amount), account);

            if (success){
                ctx.status(201);
            } else{
                ctx.status(400);
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean handleWithdraw(Context ctx){
        ObjectMapper mapper = new ObjectMapper();
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        User user = userService.getByUserId(id);
        String accountPara = ctx.pathParam("account");
        int account = Integer.parseInt(accountPara);
        deposit d;
        try {
            d = mapper.readValue(ctx.body(),deposit.class);
            boolean success = userService.withdraw(user, id, Double.parseDouble(d.amount), account);

            if (success){
                ctx.status(201);
            } else{
                ctx.status(400);
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean handleTransfer(Context ctx){
        ObjectMapper mapper = new ObjectMapper();
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        User user = userService.getByUserId(id);
        String accountPara = ctx.pathParam("account");
        int account = Integer.parseInt(accountPara);
        deposit d;
        try {
            d = mapper.readValue(ctx.body(),deposit.class);
            boolean success = userService.transfer(user, id, Double.parseDouble(d.amount), account);

            if (success){
                ctx.status(201);
            } else{
                ctx.status(400);
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }
}

class deposit{
    public String amount;
}
